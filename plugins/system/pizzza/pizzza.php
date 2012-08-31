<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Plugins
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Pizzza system plugin.
 */
class PlgSystemPizzza extends JPlugin
{
    /**
     * @var string
     */
    private $resultBasePath;

    /**
     * Constructor.
     *
     * @param object $subject
     * @param array  $config
     */
    public function __construct(&$subject, $config = array())
    {
        $this->resultBasePath  = JPATH_PLUGINS.'/restapi/lastmod/lastmod';

        parent::__construct($subject, $config);
    }

    /**
     * Called after a SOBI entry is saved.
     *
     * @param SPEntry $sobiEntry
     *
     * @throws DomainException
     *
     * @return PlgSystemPizzza
     */
    public function EntryAfterSave(SPEntry $sobiEntry)
    {
        $path = $this->resultBasePath.'/sobi.xml';

        /* @var SimpleXMLElement $xml */
        $xml = simplexml_load_string('<xml/>');
        $xml->addChild('lastmod', time());

        if(false == $xml->asXML($path))
            throw new DomainException(__METHOD__.' - Can not write the lastmod.xml');


       // $component = JComponentHelper::getComponent('com_pizzza');

     //   JModel::addIncludePath(JPATH_ADMINISTRATOR.'/components/com_pizza/models');

     //   return $this;
    }
}
