<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Plugins
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * SOBI REST Plugin.
 */
class PlgRestapiSobi extends JPlugin
{
    /**
     * @var JDatabase
     */
    private $db = null;

    public function onRestCall($type, $id)
    {
        // @todo ACL and other stuff

        $list = array();
        $this->db = JFactory::getDbo();

        switch($type)
        {
            case 'section' :
                //-- Get all categories and items by section
                $categories = $this->getSobiCategories((int)$id);
                break;

            case 'category' :
                //-- Get all items by category
                $category = new stdClass;
                $category->name = '';
                $category->id = (int)$id;
                $categories = array($category);
                break;

            case 'item' :
                //-- Get a single item
                return array($this->getSobiItem((int)$id));
                break;

            default :
                throw new InvalidArgumentException('Invalid SOBI call');
        }

        foreach($categories as $category)
        {
            $catList = array();

            $catList['name'] = $category->name;
            $catList['items'] = array();

            $items = $this->getSobiItems($category->id);

            foreach($items as $item)
            {
                $catList['items'][] = $this->fieldsToItem($this->getSobiItem($item->id), $category->id);
            }

            $list[] = $catList;
        }

        return $list;
    }

    private function fieldsToItem($fields, $categoryId = 0)
    {
        $item = new stdClass;
        $item->catid = $categoryId;

        foreach($fields as $field)
        {
            $item->{$field->fName} = $field->fValue;
        }

        return $item;
    }

    /**
     * Get SOBI categories by section.
     *
     * @param integer $sectionId
     *
     * @return mixed
     */
    private function getSobiCategories($sectionId)
    {
        $query = $this->db->getQuery(true)
            ->from('#__sobipro_object AS sobj')
            ->leftJoin('#__sobipro_relations AS srel ON srel.id=sobj.id')
            ->select('sobj.id, sobj.nid, sobj.name')
            ->where('sobj.parent='.$sectionId)
            ->order('srel.position');

        return $this->db->setQuery($query)->loadObjectList();
    }

    /**
     * Get SOBI items by category.
     *
     * @param integer $categoryId
     *
     * @return array
     */
    private function getSobiItems($categoryId)
    {
        static $query;

        if( ! $query) $query = $this->db->getQuery(true);

        $query->clear()
            ->select('srel.id')
            ->from('#__sobipro_relations AS srel')
            ->where('srel.pid='.(integer)$categoryId);

        return $this->db->setQuery($query)->loadObjectList();
    }

    /**
     * Get a single SOBI item.
     *
     * @param integer $id
     *
     * @return object
     */
    private function getSobiItem($id)
    {
        static $query;

        if( ! $query) $query = $this->db->getQuery(true);

        $query->clear()
            ->select('sfie.nid AS fName, sfda.baseData AS fValue')
            ->from('#__sobipro_field_data AS sfda')
            ->leftJoin('#__sobipro_field AS sfie ON sfie.fid=sfda.fid')
            ->where('sfda.sid='.(integer)$id)
            ->group('sfda.fid')
            ->where('lang='.$this->db->quote('es-ES'));

        return $this->db->setQuery($query)->loadObjectList();
    }
}
