<?php
/**
 * @package    Pizzza
 * @subpackage Views
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


jimport('joomla.application.component.view');

/**
 * HTML View class for the Pizzza component.
 *
 * @static
 * @package	Pizzza
 * @subpackage	Views
 * @since 1.0
 */
class PizzzaViewCategories extends JView
{
    /**
     * PizzzaList view display method.
     *
     * @param string $tpl The name of the template file to parse;
     *
     * @return void
     */
    public function display($tpl = null)
    {
        $categories	= $this->get('data');

        //-- Get the page/component configuration
        $params = JComponentHelper::getParams('com_pizzza');

        $menu = JSite::getMenu()->getActive();

        //-- Because the application sets a default page title, we need to get it
        //-- right from the menu item itself
        if(is_object($menu))
        {
            if( ! $menu->params->get('page_title'))
            {
                $params->set('page_title', JText::_('Pizzza'));
            }
        }
        else
        {
            $params->set('page_title', JText::_('Pizzza'));
        }

        JFactory::getDocument()->setTitle($params->get('page_title'));

        //-- Set some defaults if not set for params
        $params->def('comp_description', JText::_('Pizzza_DESC'));

        //-- Define image tag attributes
        if($params->get('image') != -1)
        {
            $attribs['align'] =($params->get('image_align') != '') ? $params->get('image_align') : '';
            $attribs['hspace'] = 6;

            //-- Use the static HTML library to build the image tag
            $image = JHTML::_('image', 'images/stories/'.$params->get('image'), JText::_('Pizzza'), $attribs);
        }

        for($i = 0; $i < count($categories); $i++)
        {
            $category =& $categories[$i];
            $category->link = JRoute::_('index.php?option=com_pizzza&view=category&id='.$category->slug);

            //-- Prepare category description
            $category->description = JHTML::_('content.prepare', $category->description);
        }//for

        $this->assignRef('image', $image);
        $this->assignRef('params', $params);
        $this->assignRef('categories', $categories);

        parent::display($tpl);
    }//function
}//class
