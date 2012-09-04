<?php
/**
 * @package    Pizzza
 * @subpackage Models
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


jimport('joomla.application.component.model');

/**
 * Pizzza Categories Model.
 *
 * @package Pizzza
 * @subpackage Models
 */
class PizzzaModelCategories extends JModel
{
    /**
     * Categories data array
     *
     * @var array
     */
    private $_data = null;

    /**
     * Categories total
     *
     * @var integer
     */
    private $_total = null;

    /**
     * Constructor
     *
     * @since
     */

    /**
     * Method to get Pizzza item data for the category.
     *
     * @access public
     * @return array
     */
    public function getData()
    {
        // Lets load the content if it doesn't already exist
        if(empty($this->_data))
        {
            $query = $this->_buildQuery();
            $this->_data = $this->_getList($query);
        }

        return $this->_data;
    }//function

    /**
     * Method to get the total number of Pizzza items for the category.
     *
     * @access public
     * @return integer
     */
    public function getTotal()
    {
        // Lets load the content if it doesn't already exist
        if(empty($this->_total))
        {
            $query = $this->_buildQuery();
            $this->_total = $this->_getListCount($query);
        }

        return $this->_total;
    }//function

    /**
     * Build the SELECT query.
     *
     * @return string
     */
    public function _buildQuery()
    {
        //-- Query to retrieve all categories that belong to the Pizzza extension.
        //-- and that are published.

        $db = $this->_db;

        $query = $db->getQuery(true);

        $query->from($db->quoteName('#__categories').' AS cc')
            ->select('cc.*, COUNT(a.id) AS numitems')
            ->select('cc.id as slug')
            ->join('left', $db->quoteName('#__pizzza').' AS a ON a.catid = cc.id')
            ->where('extension = '.$db->quote('com_pizzza'))
            ->where('cc.published = 1')
            ->group('cc.id');

        return $query;
    }//function
}//class
