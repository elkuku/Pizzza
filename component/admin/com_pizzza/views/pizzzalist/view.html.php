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
 * HTML View class for the Pizzza Component.
 *
 * @package    Pizzza
 * @subpackage Views
 */
class PizzzaViewPizzzaList extends JView
{
    /**
     * PizzzaList view display method
     *
     * @param null $tpl
     *
     * @return void
     */
    public function display($tpl = null)
    {
        //-- Get data from the model
        $this->items = $this->get('Items');

        //-- Get a JPagination object
        $this->pagination = $this->get('Pagination');

        // Die Toolbar hinzufügen
        $this->addToolBar();

        // Auf Fehler prüfen
        $errors = $this->get('Errors');

        if(count($errors))
        {
            JFactory::getApplication()->enqueueMessage(implode('<br />', $errors), 'error');

            return;
        }

        // Das Template wird aufgerufen
        parent::display($tpl);

        // Set the document
        $this->setDocument();
    }//function

    /**
     * Setting the toolbar
     */
    protected function addToolBar()
    {
        JToolBarHelper::title(JText::_('COM_PIZZZA_MANAGER_PIZZZALIST')
        , 'pizzza');

        JToolBarHelper::addNew('pizzza.add');
        JToolBarHelper::editList('pizzza.edit');
        JToolBarHelper::deleteList('', 'pizzzalist.delete');

        // CSS class for the 48x48 toolbar icon
        JFactory::getDocument()->addStyleDeclaration(
       '.icon-48-pizzza'
       .' {background-image: url(components/com_pizzza/assets/images/com_pizzza-48.png)}');
    }//function

    /**
     * Method to set up the document properties
     *
     * @return void
     */
    protected function setDocument()
    {
        JFactory::getDocument()->setTitle(JText::_('COM_PIZZZA_PIZZZA_ADMINISTRATION'));
    }//function
}//class
