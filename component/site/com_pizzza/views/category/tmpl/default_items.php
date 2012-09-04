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


?>
<script language="javascript" type="text/javascript">
function tableOrdering(order, dir, task)
{
    var form = document.adminForm;

    form.filter_order.value = order;
    form.filter_order_Dir.value = dir;

    document.adminForm.submit(task);
}//function
</script>

<form action="<?php echo JFilterOutput::ampReplace($this->action); ?>" method="post" name="adminForm">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
        <td align="right" colspan="4">
        <?php
            echo JText::_('Display Num').'&nbsp;';
            echo $this->pagination->getLimitBox();
        ?>
        </td>
    </tr>
<?php if ($this->params->def('show_headings', 1)) : ?>
    <tr>
        <td width="10" style="text-align:right;"
            class="sectiontableheader<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
            <?php echo JText::_('Num'); ?>
        </td>
        <td width="10" style="text-align:right;"
            class="sectiontableheader<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
            <?php echo JText::_('Id'); ?>
        </td>
<?php  ?>
        <td height="20" class="sectiontableheader<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
            <?php echo JHTML::_('grid.sort', 'Primary key', 'id', $this->lists['order_Dir'], $this->lists['order']); ?>
        </td>
        <td height="20" class="sectiontableheader<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
            <?php echo JHTML::_('grid.sort', 'Category id', 'catid', $this->lists['order_Dir'], $this->lists['order']); ?>
        </td>
        <td height="20" class="sectiontableheader<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
            <?php echo JHTML::_('grid.sort', 'Title', 'title', $this->lists['order_Dir'], $this->lists['order']); ?>
        </td>
<?php  ?>
    </tr>
<?php endif; ?>

<?php foreach($this->items as $item) : ?>
    <tr class="sectiontableentry<?php echo $item->odd + 1; ?>">
        <td align="right">
            <?php echo $this->pagination->getRowOffset($item->count); ?>
        </td>
        <td height="20">
            <?php echo $item->link; ?>
        </td>
<?php  ?>
        <td>
            <?php echo $item->id; ?>
        </td>
        <td>
            <?php echo $item->catid; ?>
        </td>
        <td>
            <?php echo $item->title; ?>
        </td>
<?php  ?>
    </tr>
<?php endforeach; ?>

    <tr>
        <td align="center" colspan="4"
            class="sectiontablefooter<?php echo $this->escape($this->params->get('pageclass_sfx')); ?>">
        <?php echo $this->pagination->getPagesLinks(); ?>
        </td>
    </tr>
    <tr>
        <td colspan="4" align="right" class="pagecounter">
            <?php echo $this->pagination->getPagesCounter(); ?>
        </td>
    </tr>
</table>

<input type="hidden" name="filter_order" value="<?php echo $this->lists['order']; ?>" />
<input type="hidden" name="filter_order_Dir" value="" />
</form>
