<?php
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


//-- Import the class JControllerForm
jimport('joomla.application.component.controllerform');

/**
 * Pizzza Controller.
 *
 * @package    Pizzza
 * @subpackage Controllers
 */
class PizzzaControllerPizzza extends JControllerForm
{
    /**
     * !!!
     * If our controller does not follow the standard pluralisation
     * we have to provide the name here
     *
     * @var string
     */
    protected $view_list = 'PizzzaList';
}//class
