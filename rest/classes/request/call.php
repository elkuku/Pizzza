<?php
/**
 * User: elkuku
 * Date: 30.08.12
 * Time: 13:03
 */
class RestRequestCall
{
    /**
     * Identify method.
     *
     * @throws InvalidArgumentException
     *
     * @return string
     */
    public static function get()
    {
        $pcs = explode('/', trim($_SERVER['REQUEST_URI'], '/'));
        $url = parse_url(end($pcs));

        if(false == isset($url['path']))
            throw new InvalidArgumentException(__METHOD__.' - Invalid call', 4);

        $pcs = explode('.', $url['path']);

        if(false == isset($pcs[0]))
            throw new InvalidArgumentException(__METHOD__.' - Invalid call', 5);

        return $pcs[0];
    }
}
