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


/**
 *  Require the base controller.
 */
require_once JPATH_COMPONENT.DS.'controller.php';

// Require specific controller if requested
$controller = JRequest::getCmd('controller');

if($controller)
{
    $path = JPATH_COMPONENT.DS.'controllers'.DS.$controller.'.php';

    if(file_exists($path))
    {
        require_once $path;
    }
    else
    {
        $controller = '';
    }
}

//-- Create the controller
$classname = 'PizzzaController'.$controller;

$controller = new $classname;

//-- Perform the Request task
$controller->execute(JRequest::getCmd('task'));

//-- Redirect if set by the controller
$controller->redirect();
