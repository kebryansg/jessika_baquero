/**
 * @author David Graham <prograhammer@gmail.com>
 * @version v1.1.4
 * @link https://github.com/prograhammer/bootstrap-table-contextmenu
 */
!function(t){"use strict";t.extend(t.fn.bootstrapTable.defaults,{contextMenu:void 0,contextMenuTrigger:"right",contextMenuAutoClickRow:!0,contextMenuButton:void 0,beforeContextMenuRow:function(t,n,e){},onContextMenuItem:function(t,n){return!1},onContextMenuRow:function(t,n){return!1}}),t.fn.bootstrapTable.methods.push("showContextMenu"),t.extend(t.fn.bootstrapTable.Constructor.EVENTS,{"contextmenu-item.bs.table":"onContextMenuItem","contextmenu-row.bs.table":"onContextMenuRow"});var n=t.fn.bootstrapTable.Constructor,e=n.prototype.initBody;n.prototype.initBody=function(){e.apply(this,Array.prototype.slice.apply(arguments)),(this.options.contextMenu||this.options.contextMenuButton||this.options.beforeContextMenuRow)&&this.initContextMenu()},n.prototype.initContextMenu=function(){var n=this;("right"==n.options.contextMenuTrigger||"both"==n.options.contextMenuTrigger)&&n.$body.find("> tr[data-index]").off("contextmenu.contextmenu").on("contextmenu.contextmenu",function(e){var o=n.data[t(this).data("index")],i=n.options.beforeContextMenuRow.apply(this,[e,o,null]);return i!==!1&&n.showContextMenu({event:e}),!1}),("left"==n.options.contextMenuTrigger||"both"==n.options.contextMenuTrigger)&&n.$body.find("> tr[data-index]").off("click.contextmenu").on("click.contextmenu",function(e){var o=n.data[t(this).data("index")],i=n.options.beforeContextMenuRow.apply(this,[e,o,null]);return i!==!1&&n.showContextMenu({event:e}),!1}),"string"==typeof n.options.contextMenuButton&&n.$body.find("> tr[data-index]").find(n.options.contextMenuButton).off("click.contextmenu").on("click.contextmenu",function(e){var o=n.data[t(this).closest("tr[data-index]").data("index")],i=n.options.beforeContextMenuRow.apply(this,[e,o,this]);return i!==!1&&n.showContextMenu({event:e,buttonElement:this}),!1})},n.prototype.showContextMenu=function(n){function e(n,e,o,i){var u=t(window)[o](),c=t(window)[i](),r=n[o](),a=e+c;return e+r>u&&e>r&&(a-=r),a}if(!n||!n.event)return!1;if(n&&!n.contextMenu&&"string"!=typeof this.options.contextMenu)return!1;var o,i,u,c=this,r=t(n.event.target).closest("tr[data-index]"),a=c.data[r.data("index")];n&&!n.contextMenu&&"string"==typeof this.options.contextMenu&&(i=n.event.clientX,u=n.event.clientY,o=t(this.options.contextMenu)),n&&n.contextMenu&&(i=n.event.clientX,u=n.event.clientY,o=t(n.contextMenu)),n&&n.buttonElement&&(i=n.buttonElement.getBoundingClientRect().left,u=n.buttonElement.getBoundingClientRect().bottom),o.find("li").off("click.contextmenu").on("click.contextmenu",function(n){var e=c.data[o.data("index")];c.trigger("contextmenu-item",e,t(this))}),t(document).triggerHandler("click.contextmenu"),t(document).off("click.contextmenu").on("click.contextmenu",function(n){(c.pageX!=n.pageX||c.pageY!=n.pageY)&&(o.hide(),t(document).off("click.contextmenu"))}),c.pageX=n.event.pageX,c.pageY=n.event.pageY,o.data("index",r.data("index")).appendTo(t("body")).css({position:"absolute",left:e(o,i,"width","scrollLeft"),top:e(o,u,"height","scrollTop"),zIndex:1100}).show(),c.trigger("contextmenu-row",a,r),c.options.contextMenuAutoClickRow&&"right"==c.options.contextMenuTrigger&&c.trigger("click-row",a,r)}}(jQuery);