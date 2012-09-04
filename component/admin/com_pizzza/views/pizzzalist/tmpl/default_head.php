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
<tr>
    <th width="5">
        <?= JText::_('COM_PIZZZA_PIZZZA_HEADING_EDIT'); ?>
    </th>
    <th width="5">
        <input type="checkbox" name="toggle" value="" onclick="checkAll(<?= count($this->items); ?>);"/>
    </th>

    <th width="5"><?= JText::_('Primary key'); ?></th>
    <th width="5"><?= JText::_('Category id'); ?></th>
    <th><?= JText::_('Title'); ?></th>
    <th><?= JText::_('Type'); ?></th>
    <th><?= JText::_('Type id'); ?></th>

</tr>
