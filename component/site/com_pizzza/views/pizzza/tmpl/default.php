<?php
/**
 * @package    Pizzza
 * @subpackage Views
 * @author     Nikolai Plath {@link https://github.com/elkuku}
 * @author     Created on 30-Aug-2012
 * @license    GNU/GPL
 */

//-- No direct access
defined('_JEXEC') || die('=;)');


JHTML::stylesheet('default.css', 'components/com_pizzza/assets/css/');

?>
<div id="com_pizzza_content">
	<h1 class="componentheading">Pizzza</h1>
<?php if( ! $this->data) : ?>
    <h3><?php echo JText::_('No records found'); ?></h3>
<?php else : ?>
<?php $row = $this->data; ?>
<?php  ?>
            <div class="title">Primary key</div>
            <div class="cell"><?php echo $row->id; ?></div>
            <div class="title">Category id</div>
            <div class="cell"><?php echo $row->catid; ?></div>
            <div class="title">Title</div>
            <div class="cell"><?php echo $row->title; ?></div>
<?php  ?>
<?php endif; ?>
</div>
