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
<?php foreach($this->items as $i => $row): ?>
<?php $link = JRoute::_('index.php?option=com_pizzza&view=pizzza&layout=edit&id='
        .$row->id); ?>
<tr class="row<?php echo $i % 2; ?>">
    <td>
        <a href="<?php echo $link; ?>">
            <?php echo $row->id; ?>
        </a>
    </td>
    <td>
        <?php echo JHtml::_('grid.id', $i, $row->id); ?>
    </td>

    <td>
        <?php echo $row->id; ?>
    </td>
    <td>
        <?php echo $row->catid; ?>
    </td>
    <td>
        <?php echo $row->title; ?>
    </td>
    <td>
        <?php echo $row->type; ?>
    </td>
    <td>
        <?php echo $row->tid; ?>
    </td>
    <?php  ?>
</tr>
<?php endforeach;
