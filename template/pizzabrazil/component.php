<?php
/**
 * @package    PizzaBrazil
 * @subpackage Base
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 18-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


$baseUrl = $this->baseurl ?>/templates/<?php $this->template;

?>
<!DOCTYPE html>
<html lang="<?php echo $this->language; ?>"
      dir="<?php echo $this->direction; ?>">
<head>
    <jdoc:include type="head"/>
    <link rel="stylesheet" href="<?php echo $baseUrl; ?>/css/template.css" type="text/css"/>

    <?php if($this->direction == 'rtl') : ?>
    <link rel="stylesheet" href="<?php echo $baseUrl; ?>/css/template_rtl.css" type="text/css"/>
    <?php endif; ?>
</head>
<body class="contentpane">
<jdoc:include type="message"/>
<jdoc:include type="component"/>
</body>
</html>
