<?php
/**
 * @package    Pizzza
 * @subpackage Tables
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


/**
 * Pizzza Table class.
 *
 * @package    Pizzza
 * @subpackage Components
 */
class PizzzaTablePizzza extends JTable
{
    /**
     * Constructor.
     *
     * @param object &$db Database connector object
     */
    public function __construct(& $db)
    {
        parent::__construct('#__pizzza', 'id', $db);
    }
}
