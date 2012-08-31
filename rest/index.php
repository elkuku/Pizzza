<?php
/**
 * @package    Pizzza
 * @subpackage REST
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

// We are a valid Joomla entry point.
define('_JEXEC', 1);

// Setup the base path related constant.
define('JPATH_BASE', dirname(dirname($_SERVER['SCRIPT_FILENAME'])));

ini_set('display_errors', true); //@debug
error_reporting(- 1); //@debug

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
     * @throws RuntimeException
     *
     * @return  void
     */
    protected function doExecute()
    {
        $response = new RestResponseJson;

        try
        {
            RestLoginHelper::login();

            $rest = RestRequestCall::parseCall();

            JPluginHelper::importPlugin('restapi', $rest->call);

            $pluginResult = JDispatcher::getInstance()
                ->trigger('onRestCall', $rest->commands);

            if(false == isset($pluginResult[0]))
                throw new RuntimeException('No plugin result - disabled ?', 66);

            $response->setData($pluginResult[0]);
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
                ->setStatus($e->getCode() ? : 1);
        }

        $this->setBody((string)$response);

        JApplication::getInstance('site')
            ->logout();
    }

    /**
     * This is used by Joomla!'s auth plugin to load the language...
     *
     * @return bool
     */
    public function isAdmin()
    {
        return false;
    }

    public function checkSession()
    {
        JApplication::getInstance('site')
            ->checkSession();
    }

    public function getClientId()
    {
        return JApplication::getInstance('site')
            ->getClientId();
    }
}

/**
 * I am a dummy :(...
 */
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
    JApplication::getInstance('site')
        ->logout();

    echo new RestResponseJson($e);
}
