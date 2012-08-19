<?php
/**
 * @package    Pizzza
 * @subpackage Views
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


//-- Import the JView class
jimport('joomla.application.component.view');

/**
 * HTML View class for the Pizzza Component.
 *
 * @package Pizzza
 */
class PizzzaViewPizzza extends JView
{
    /**
     * Pizzza view display method.
     *
     * @param string $tpl The name of the template file to parse;
     *
     * @return void
     */
    public function display($tpl = null)
    {
        $this->greeting = "Hello World!";

        parent::display($tpl);
    }//function
}//class
