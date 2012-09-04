<?php
/**
 * @package    Pizzza
 * @subpackage Models
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');

//-- Import the class JModelList
jimport('joomla.application.component.modellist');

/**
 * PizzzaList Model.
 *
 * @package    Pizzza
 * @subpackage Models
 */
class PizzzaModelPizzzaList extends JModelList
{
    /**
     * Method to build an SQL query to load the list data.
     * Funktion um einen SQL Query zu erstellen der die Daten für die Liste läd.
     *
     * @return string SQL query
     */
    protected function getListQuery()
    {
        // Ein Datenbankobjekt beziehen.
        $db = JFactory::getDBO();

        // Ein neues (leeres) Queryobjekt beziehen.
        $query = $db->getQuery(true);

        $query->from('#__pizzza AS a ');
        $query->leftjoin(' #__categories AS b ON b.id = a.catid');
        $query->select('a.id, a.catid, a.title, a.type, a.tid, b.title AS category');

        return $query;
    }
}
