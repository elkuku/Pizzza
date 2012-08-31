<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage REST.classes
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Login helper class.
 */
class RestLoginHelper
{
    public static function login()
    {
        $application = JApplication::getInstance('site');
        $input = $application->input;

        $credentials['username'] = $input->getString('u');
        $credentials['password'] = $input->getString('p');

        if($credentials['username'] && $credentials['password'])
        {
            $options = array(//---- WTF.... @todo
                'autoregister' => false,
                'group' => 'Administrator'
            );

            if(false === $application->login($credentials, $options))
                throw new RestExceptionAuthentication;
        }

        return true;
    }
}
