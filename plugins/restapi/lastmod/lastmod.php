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
class PlgRestapiLastmod extends JPlugin
{
    public function onRestCall()
    {
        $result = array();

        /* @var SplFileInfo $fileInfo */
        foreach(new DirectoryIterator(__DIR__.'/lastmod') as $fileInfo)
        {
            if($fileInfo->isDot())
                continue;

            $xml = simplexml_load_file($fileInfo->getPathname());

            $name = $fileInfo->getBasename();

            if(false === $xml)
                throw new RuntimeException('Unable to read the xml file: '.$name);

            $result[substr($name, 0, strpos($name, '.'))] = (string)$xml->lastmod;
        }

        return $result;
    }
}
