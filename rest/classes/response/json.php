<?php defined('_JEXEC') || die('=;)');
/**
 * @package    Pizzza
 * @subpackage Controllers
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

/**
 * JSON response class.
 */
final class RestResponseJson
{
    /**
     * Determines whether the request was successful.
     * Status != 0 means error.
     *
     * @var integer
     */
    private $status = 0;

    /**
     * The main response message
     *
     * @var string
     */
    private $message = '';

    /**
     * The response data
     *
     * @var object
     */
    private $data = null;

    /**
     * Constructor.
     *
     * @param object  $response  The Response data
     * @param string  $message   The main response message
     */
    public function __construct($response = null, $message = '')
    {
        $this->message = $message;

        // Get the message queue
        // Check if we are dealing with an error
        if($response instanceof Exception)
        {
            // Prepare the error response
            $this->status = ($response->getCode()) ? : 1;
            $this->message = $response->getMessage();
        }
        else
        {
            // Prepare the response data
            $this->status = 0;
            $this->data = $response;
        }
    }

    /**
     * Set the response status.
     *
     * @param $status
     *
     * @return RestResponseJson
     */
    public function setStatus($status)
    {
        $this->status = (integer)$status;

        return $this;
    }

    /**
     * Set the response message.
     *
     * @param $message
     *
     * @return RestResponseJson
     */
    public function setMessage($message)
    {
        $this->message = (string)$message;

        return $this;
    }

    /**
     * Set the data for the request.
     *
     * @param mixed $data Object or array
     *
     * @throws RuntimeException
     *
     * @return RestResponseJson
     */
    public function setData($data)
    {
        if(false == is_object($data) && false == is_array($data))
            throw new RuntimeException(__METHOD__.' - Data must be either object or array');

        $this->data = $data;

        return $this;
    }

    /**
     * Magic toString method for sending the response in JSON format.
     *
     * @return  string  The response in JSON format
     */
    public function __toString()
    {
        return json_encode(get_object_vars($this));
    }

}
