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


// Die Joomla! Controllerbibliothek importieren
jimport('joomla.application.component.controller');

// Die Helperdatei registrieren
JLoader::register('PizzzaHelper', JPATH_COMPONENT.'/helpers/pizzza.php');

// Eine Instanz des Controllers mit dem Präfix 'HalloWelt' beziehen
$controller = JController::getInstance('Pizzza');

// Den 'task' der im Request übergeben wurde ausführen
$controller->execute(JRequest::getCmd('task'));

// Einen Redirect durchführen wenn er im Controller gesetzt ist
$controller->redirect();
