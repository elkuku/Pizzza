<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Plugins
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Content REST Plugin.
 */
class PlgRestapiContent extends JPlugin
{
    public function onRestCall($type, $id)
    {
        // @todo ACL and other stuff

        $db = JFactory::getDbo();

        $query = $db->getQuery(true);

        $query->select('c.title, c.introtext AS text, c.created AS date')
            ->from('#__content AS c');

        switch($type)
        {
            case 'category':
                $query->where('c.catid='.(int)$id);
                break;

            case 'article':
                $query->where('c.id='.(int)$id);
                break;

            default :
                throw new InvalidArgumentException('Invalid content call');
        }

        return $db->setQuery($query)->loadObjectList();
    }
}
