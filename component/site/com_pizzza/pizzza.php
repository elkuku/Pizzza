<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Base
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

require JPATH_COMPONENT_ADMINISTRATOR.'/helpers/loader.php';

//-- Import the class JController
jimport('joomla.application.component.controller');

//-- Get an instance of the controller with the prefix 'Pizzza'
$controller = JController::getInstance('Pizzza');

//-- Execute the 'task' from the Request
$controller->execute(JRequest::getCmd('task'));

//-- Redirect if set by the controller
$controller->redirect();
