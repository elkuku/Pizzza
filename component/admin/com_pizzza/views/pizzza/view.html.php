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
class PizzzaViewPizzza extends JView
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
        // Die Daten werden bezogen
        $this->item = $this->get('Item');

        // Das Formular
        $this->form = $this->get('Form');

        // JavaScript
        $this->script = $this->get('Script');

        // Auf Fehler prüfen
        $errors = $this->get('Errors');

        if(count($errors))
        {
            JFactory::getApplication()->enqueueMessage(implode('<br />', $errors), 'error');

            return;
        }

        // Die Toolbar hinzufügen
        $this->addToolBar();

        // Das Template wird aufgerufen
        parent::display($tpl);

        // Set the document
        $this->setDocument();
    }

    //function

    /**
     * Setting the toolbar
     */
    protected function addToolBar()
    {
        JRequest::setVar('hidemainmenu', true);

        $isNew = ($this->item->id == 0);

        JToolBarHelper::title($isNew
                ? JText::_('COM_PIZZZA_MANAGER_PIZZZA_NEW')
                : JText::_('COM_PIZZZA_MANAGER_PIZZZA_EDIT')
            , 'pizzza');

        JToolBarHelper::save('pizzza.save');

        JToolBarHelper::cancel('pizzza.cancel'
            , $isNew
                ? 'JTOOLBAR_CANCEL'
                : 'JTOOLBAR_CLOSE');

        // CSS Klasse für das 48x48 Icon der Toolbar
        JFactory::getDocument()->addStyleDeclaration(
            '.icon-48-pizzza {background-image: url('
                .'components/com_pizzza/assets/images/com_pizzza-48.png);}'
        );
    }

    //function

    /**
     * Method to set up the document properties
     *
     * @return void
     */
    protected function setDocument()
    {
        $isNew = ($this->item->id < 1);

        $document = JFactory::getDocument();

        $document->setTitle($isNew
            ? JText::_('COM_PIZZZA_PIZZZA_CREATING')
            : JText::_('COM_PIZZZA_PIZZZA_EDITING'));

        $document->addScript(JURI::root(true).$this->script);

        $document->addScript(JURI::root(true)
            .'/administrator/components/com_pizzza/views/pizzza/submitbutton.js');

        JText::script('COM_PIZZZA_PIZZZA_ERROR_UNACCEPTABLE');
    }
    //function
}//class
