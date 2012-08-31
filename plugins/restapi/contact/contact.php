<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Plugins
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Contact REST Plugin.
 */
class PlgRestapiContact extends JPlugin
{
    public function onRestCall($type, $id)
    {
        // @todo ACL and other stuff

        switch($type)
        {
            case 'id':
                //-- Get a single item
                break;

            default :
                throw new InvalidArgumentException('Invalid contact call');
        }

        $db = JFactory::getDbo();

        $query = $db->getQuery(true)
            ->from('#__contact_details AS c')
            ->select('c.id, c.name, c.address, c.suburb, c.telephone, c.fax, c.mobile')
            ->select('c.email_to, c.webpage, c.misc')
            ->where('id='.(int)$id);

        return $db->setQuery($query)->loadObject();
    }
}
