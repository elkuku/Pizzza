<?php

/**
 * Pizzzas model.
 */
class ModelPizzzas extends JModel
{
    public function listAll()
    {
        $sobiSectionId = 54;
        $list = array();

        $query = $this->_db->getQuery(true);

        $query->from('#__sobipro_object AS sobj')
            ->select('sobj.id, sobj.nid, sobj.name')
            ->where('sobj.parent='.$sobiSectionId)
            ->leftJoin('#__sobipro_relations AS srel ON srel.id=sobj.id')
            ->order('srel.position');

        $categories = $this->_db->setQuery($query)->loadObjectList();

        foreach($categories as $category)
        {
            $catList = array();

            $catList['name'] = $category->name;
            $catList['items'] = array();

            $query->clear()
                ->from('#__sobipro_relations AS srel')
                ->select('srel.id')
                ->where('srel.pid='.$category->id);

            $items = $this->_db->setQuery($query)->loadObjectList();

            foreach($items as $item)
            {
                $query->clear()
                    ->from('#__sobipro_field_data AS sfda')
                    ->select('sfda.baseData')
                    ->where('sfda.sid='.$item->id)
                    ->group('sfda.fid')
                    ->where('lang='.$this->_db->quote('es-ES'));

                $entry = $this->_db->setQuery($query)->loadObjectList();
//var_dump($entry);
                $e = new stdClass;
                $e->catid = $category->id;
                $e->title = $entry[0]->baseData;
                $e->description = $entry[1]->baseData;
                $e->price_peq =(isset($entry[2])) ? $entry[2]->baseData : 0;
                $e->price_med =(isset($entry[3])) ? $entry[3]->baseData : 0;
                $e->price_gra =(isset($entry[4])) ? $entry[4]->baseData : 0;

                $catList['items'][] = $e;
            }

            $list[] = $catList;
        }

        return $list;
    }
}
