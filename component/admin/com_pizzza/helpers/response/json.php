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
class PzaResponseJson
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
    private $message = null;

    /**
     * Array of messages gathered in the JApplication object
     *
     * @var array
     */
    private $messages = null;

    /**
     * The response data
     *
     * @var object
     */
    private $data = null;

    /**
     * Constructor
     *
     * @param object  $response  The Response data
     * @param string  $message   The main response message
     */
    public function __construct($response = null, $message = null)
    {
        $this->message = $message;

        // Get the message queue
        $messages = JFactory::getApplication()->getMessageQueue();
        $lists = array();

        // Build the sorted messages list
        if(is_array($messages) && count($messages))
        {
            foreach($messages as $message)
            {
                if(isset($message['type']) && isset($message['message']))
                {
                    $lists[$message['type']][] = $message['message'];
                }
            }
        }

        // If messages exist add them to the output
        if(count($lists))
        {
            $this->messages = $lists;
        }

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
     * Magic toString method for sending the response in JSON format
     *
     * @return    string    The response in JSON format
     */
    public function __toString()
    {
        return json_encode(get_object_vars($this));
    }
}
