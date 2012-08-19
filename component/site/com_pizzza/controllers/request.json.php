<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

//jimport('joomla.application.component.controller');

/**
 * Request controller.
 *
 * @package    Pizzza
 * @subpackage Controllers
 */
class PizzzaControllerRequest extends JController
{
    /**
     * @var PzaResponseJson
     */
    private $response = null;

    public function __construct($config = array())
    {
        parent::__construct($config);
    }

    public function listall()
    {
        $data = new stdClass;

        $data->a = 'huhu';
        $data->b = 'schuhu';

        $this->response = new PzaResponseJson($data);
$dd = (string)$this->response;
        echo $this->response;
    }
}
