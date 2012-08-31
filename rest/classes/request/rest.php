<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage REST.classes
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Rest request base class.
 */
class RestRequestRest
{
    public $call = '';

    public $commands = array();

    public $format = 'json';

    public $apiVersion = '';
}
