<?php
/**
 * @package    Pizzza
 * @subpackage Base
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


jimport('joomla.application.component.controller');

/**
 * Pizzza default Controller.
 *
 * @package    Pizzza
 * @subpackage Controllers
 */
class PizzzaController extends JController
{
    /**
     * Method to display the view.
     *
     * @return void
     */
    public function display($cachable = false, $urlparams = false)
    {
        //-- Setting the default view
        JRequest::setVar('view', JRequest::getCmd('view', 'Pizzzalist'));

        parent::display($cachable, $urlparams);

        PizzzaHelper::addSubmenu('pizzzalist');
    }
}
