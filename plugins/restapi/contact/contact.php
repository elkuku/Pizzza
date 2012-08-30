<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Contact REST Plugin.
 */
class PlgRestapiContact extends JPlugin
{
    public function onRestCall()
    {
        $db = JFactory::getDbo();

        $contactId = JFactory::getApplication()->input->getInt('id');

        if(0 == $contactId)
            throw new InvalidArgumentException(__METHOD__.' - Invalid arguments');

        $query = $db->getQuery(true)
            ->from('#__contact_details AS c')
            ->select('c.id, c.name, c.address, c.suburb, c.telephone, c.fax, c.mobile')
            ->select('c.email_to, c.webpage, c.misc')
            ->where('id='.$contactId);

        return $db->setQuery($query)->loadObject();
    }
}
