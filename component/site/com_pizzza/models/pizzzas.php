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

            $ids = $this->_db->setQuery($query)->loadResultArray();

            if($ids)
            {
                foreach($ids as $id)
                {
                    $query->clear()
                        ->from('#__sobipro_field_data AS sfda')
                        ->select('sfda.baseData')
                        ->where('sfda.sid='.$id);

                    $entry = $this->_db->setQuery($query)->loadObjectList();

                    $e = new stdClass;
                    $e->title = $entry[0]->baseData;
                    $e->description = $entry[1]->baseData;

                    $catList['items'][] = $e;
                }
            }

            $list[] = $catList;
        }

        return $list;
    }
}
