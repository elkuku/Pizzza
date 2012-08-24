<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * Request controller.
 *
 * @package    Pizzza
 * @subpackage Controllers
 */
class PizzzaControllerRequest extends JControllerLegacy
{
    public function listall()
    {
        echo new PzaResponseJson(JModel::getInstance('Pizzzas', 'Model')->listAll());

        jexit();
    }
}
