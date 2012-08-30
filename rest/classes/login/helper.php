<?php

class RestLoginHelper
{
    public static function login()
    {
        $application = JFactory::getApplication();
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
