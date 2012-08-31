<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage REST.classes
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Request call class.
 */
class RestRequestCall
{
    /**
     * Identify method.
     *
     * @throws RuntimeException
     * @throws InvalidArgumentException
     *
     * @return RestRequestRest
     */
    public static function parseCall()
    {
        $rest = new RestRequestRest;

        $parts = explode('/', str_replace(JURI::base(), '', JURI::current()));

        if(count($parts) < 2)
            throw new RuntimeException('Invalid API call');

        $rest->apiVersion = array_shift($parts);

        switch($rest->apiVersion)
        {
            case 'v1' :
                define('REST_API', 'v1');
                break;

            default :
                throw new RuntimeException('Invalid API version');
                break;
        }

        $call = array_shift($parts);

        $callParts = explode('.', $call);

        $rest->call = $callParts[0];

        if(isset($callParts[1]))
            $rest->format = $callParts[1];

        $rest->commands = $parts;

        return $rest;
    }
}
