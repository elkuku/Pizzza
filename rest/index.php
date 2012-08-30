<?php
/**
 * @package    Pizzza
 * @subpackage Base
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

// We are a valid Joomla entry point.
define('_JEXEC', 1);

// Setup the base path related constant.
define('JPATH_BASE', dirname(dirname($_SERVER['SCRIPT_FILENAME'])));

ini_set('display_errors', true);
error_reporting(- 1);

require JPATH_BASE.'/includes/defines.php';
require JPATH_BASE.'/libraries/import.php';

JLoader::registerPrefix('Rest', __DIR__.'/classes');

/**
 * Pizzza REST interface.
 *
 * @package Pizzza
 */
class Pizzza extends JApplicationWeb
{
    /**
     * Overrides the parent doExecute method to run the web application.
     *
     * @return  void
     */
    protected function doExecute()
    {
        $response = new RestResponseJson;

        try
        {
            RestLoginHelper::login();

            JPluginHelper::importPlugin('restapi', RestRequestCall::get());

            $pluginResult = JDispatcher::getInstance()
                ->trigger('onRestCall');

            $result = (isset($pluginResult[0])) ? $pluginResult[0] : null;

            $response->setData($result);
        }
        catch(RestExceptionAuthentication $e)
        {
            $response->setMessage('Authentication failure')
                ->setStatus(4);
        }
        catch(InvalidArgumentException $e)
        {
            $response->setMessage($e->getMessage())
                ->setStatus(5);
        }
        catch(Exception $e)
        {
            $response->setMessage($e->getMessage())
                ->setStatus(69);
        }

        $this->setBody((string)$response);
    }
}

class JComponentHelper
{
    public static function getParams()
    {
        return new JRegistry;
    }
}

try
{
    $application = JApplicationWeb::getInstance('Pizzza');

    JFactory::$application = $application;

    $application->execute();
}
catch(Exception $e)
{
    echo new RestResponseJson($e);
}
