<?php
/**
 * @package    Pizzza
 * @subpackage Helpers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


/**
 * Pizzza Helper.
 *
 * @package    Pizzza
 * @subpackage Helpers
 */
abstract class PizzzaHelper
{
    /**
     * Configure the Linkbar.
     *
     * @param string $viewName The name of the active view.
     */
    public static function addSubmenu($viewName = 'pizzzalist')
    {
        JSubMenuHelper::addEntry(
        JText::_('COM_PIZZZA_LINKBAR')
        , 'index.php?option=com_pizzza&view=pizzzalist'
        , $viewName == 'pizzzalist'
        );

        JSubMenuHelper::addEntry(
        JText::_('COM_PIZZZA_CATEGORIES')
        , 'index.php?option=com_categories&extension=com_pizzza'
        , $viewName == 'categories'
        );
    }//function
}//class
