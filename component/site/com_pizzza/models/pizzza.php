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


jimport('joomla.application.component.model');

/**
 * Pizzza Model.
 *
 * @package    Pizzza
 * @subpackage Models
 */
class PizzzaModelPizzza extends JModel
{
    /**
     * Gets the Data.
     *
     * @return string The greeting to be displayed to the user
     */
    public function getData()
    {
        $id = JRequest::getInt('id');
        $db = JFactory::getDBO();

        $query = 'SELECT a.*, cc.title AS category '
        . ' FROM #__pizzza AS a '
        . ' LEFT JOIN #__categories AS cc ON cc.id = a.catid '
        . ' WHERE a.id = '.$id;

        $db->setQuery($query);
        $data = $db->loadObject();

        return $data;
    }//function
}//class
