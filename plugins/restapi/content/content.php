<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Content REST Plugin.
 */
class PlgRestapiContent extends JPlugin
{
    public function onRestCall()
    {
        $input = JFactory::getApplication()->input;

        $cid = $input->getInt('cid');
        $id = $input->getInt('id');

        $db = JFactory::getDbo();

        $query = $db->getQuery(true);

        $query->select('c.title, c.introtext AS text, c.created AS date')
            ->from('#__content AS c');

        if(0 != $cid)
        {
            $query->where('c.catid='.$cid);
        }
        elseif(0 != $id)
        {
            $query->where('c.id='.$id);
        }
        else
        {
            throw new InvalidArgumentException(__METHOD__.' - Invalid arguments');
        }

        return $db->setQuery($query)->loadObjectList();
    }

}
