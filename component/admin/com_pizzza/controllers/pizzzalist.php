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


//-- Import the Class JControllerAdmin
jimport('joomla.application.component.controlleradmin');

/**
 * Pizzza Controller.
 */
class PizzzaControllerPizzzaList extends JControllerAdmin
{
    /**
     * Proxy for getModel.
     */
    public function getModel($name = 'Pizzza', $prefix = 'PizzzaModel'
    , $config = array('ignore_request' => true))
    {
        $doSomething = 'here';

        return parent::getModel($name, $prefix, $config);
    }//function
}//class
