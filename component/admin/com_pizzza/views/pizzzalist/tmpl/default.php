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


JHtml::_('behavior.tooltip');

?>
<form action="index.php" method="post" name="adminForm" id="adminForm">
    <table class="adminlist">
        <thead><?php echo $this->loadTemplate('head');?></thead>
        <tfoot><?php echo $this->loadTemplate('foot');?></tfoot>
        <tbody><?php echo $this->loadTemplate('body');?></tbody>
    </table>

    <div>
        <input type="hidden" name="option" value="com_pizzza" />
        <input type="hidden" name="task" value="" />
        <input type="hidden" name="boxchecked" value="0" />
        <input type="hidden" name="controller" value="pizzza" />
        <?php echo JHtml::_('form.token'); ?>
    </div>
</form>
