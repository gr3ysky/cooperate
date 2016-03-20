(function (grid) {
    grid.Search = function (selector) {
        $(selector).dataTable().api().draw();
    };
    grid.Render = function (selector, url, options) {
        $(selector).addClass("table table-striped table-bordered table-hover table-responsive");
        var defaults = {
            ajax: {
                url: url,
                dataSrc: "data",
                type: "POST",
                data: getData,
            },
            minCustomButtonCountForMenu: 2,
            deferRender: true,
            processing:true,
            serverSide: true,
            bFilter: false,
            createdRow: function (row, data, index) {
                if (options.createdRow) {
                    options.createdRow(row, data, index);
                }
                $('thead th', selector).each(function (i, e) {
                    if ($(e).hasClass('hidden-xs')) {
                        $('td', row).eq(i).addClass('hidden-xs');
                    }
                });
            }
        };

        $.extend(defaults, options);
        defaults.columns=getColumns();
       // defaults.columnDefs=getColumnDefs();


        $(selector).dataTable(defaults);


        function getColumns(){
            var columns = [];
            if (options.customButtons) {
                var commands={ data: '', defaultContent: '', orderable: false, render: null, visible: true };
                var buttonscount = getCustomButtonsCount(options);
                commands.render=function( data, type, row){
                    var html = "";
                    if (buttonscount > defaults.minCustomButtonCountForMenu)
                        html = getCustomButtonsMenuHtml(options, data, type, row);
                    else html = getCustomButtonsColumnsHtml(options, data, type, row)//getCustomButtonsHtml(options,data,type,row);
                    return html;
                }
                columns.push(commands);
            }
            for (var i = 0; i < options.columns.length; i++) {
                var tableColumn = { data: '', defaultContent: '', orderable: true, render: null, visible: true };
                var column = options.columns[i];
                if (column.data != undefined)
                    tableColumn.data = column.data;

                if (column.html != undefined)
                    tableColumn.defaultContent = column.html;
                if (column.orderable != undefined)
                    tableColumn.orderable = column.orderable;
                if (column.type == 'date') {
                    var format = column.format ? column.format : 'dd.mm.yyyy';
                    tableColumn.render = function (data) {
                        if (data) {
                            var date = new Date(data);
                            return date.format(format);
                        }
                        else
                            return '';
                    }
                }
                if (column.type == 'hidden') {
                    tableColumn.visible = false;
                }
                if (column.type == 'bool') {
                    tableColumn.render = function (data) { console.log("bool",data); return data == true ? 'Evet' : 'Hayır'; }
                }
                if (column.type == 'checkbox') {
                    tableColumn.render = function (data) { return '<input type="checkbox" disabled ' + (data == true ? 'checked="checked"' : '') + ' />'; }
                }
                if (column.type == 'link') {
                    var _a = '<a href=' + column.href + ' onClick="' + (column.onClick ? 'KS.Table.processRowFunc(\'' + column.onClick + '\',this)' : '') + '">' + column.text + '</a>';
                    tableColumn.defaultContent = _a;
                }
                else if (column.href != undefined) {
                    var urlattr = 'href="' + column.href + '" ' + (column.target != undefined ? 'target="' + column.target + '"' : '');
                    tableColumn.render = function (data) { return '<a ' + urlattr + '>' + data + '</a>'; }
                }
                if (column.type == 'button') {
                    tableColumn.defaultContent = '<button type="button" class="btn btn-success" onClick="KS.Table.processRowFunc(\'' + column.onClick + '\',this)">' + column.text + '</button>';
                }

                columns.push(tableColumn);
            }
            console.log(columns)
            return columns;
        }


        function getColumnDefs() {
            if (options.customButtons) {
                return [
                    {
                        render: function (data, type, row) {

                        },
                        targets: 0
                    },
                    {"visible": getCustomButtonsCount(options) > 0, "targets": [0]}
                ];
                ;
            }
        }


        function getCustomButtonsCount(options) {
            if (options.customButtons) {
                var count = 0;
                for (var i = 0; i < options.customButtons.length; i++) {
                    if (options.customButtons[i].button.showInHeader === false) {
                        count++;
                    }
                }
                return count;
            }
            return 0;
        }

        function getData(d) {
            console.log(d);
            var data={
                draw: d.draw,
                pageSize: d.length,
                start: d.start,
                orderColumn: d.columns[d.order[0].column].data,
                orderDir: d.order[0].dir
            };
            if (options.filterForm) {
                $.extend(data, KS.Form.Serialize(options.filterForm));
            }
            return $.extend({},data, {"_csrf": $("input[name='_csrf']").val()});

        }

        function getCustomButtonsColumnsHtml(options, data, type, row) {
            console.log("getCustomButtonsColumnsHtml");
            var html = "";
            for (var i = 0; i < options.customButtons.length; i++) {
                console.log(options.customButtons[i].button);
                if (options.customButtons[i].button.showInHeader === false) {
                    hasItems = true;
                    html += getInColumnButtonHtml(options.customButtons[i].button, data, type, row);
                }
            }
            return html;
        }

        function getCustomButtonsMenuHtml(options, data, type, row) {
            console.log("getCustomButtonsMenuHtml");
            var html = '';
            if (options.customButtons) {
                html = '<div class="dropdown"><button class="btn btn-primary btn-circle btn-xs  dropdown-toggle" type="button" data-toggle="dropdown"><span class="glyphicon glyphicon-cog"></span></button> <ul class="dropdown-menu">';
                var hasItems = false;
                for (var i = 0; i < options.customButtons.length; i++) {
                    console.log(options.customButtons[i].button);
                    if (options.customButtons[i].button.showInHeader === false) {
                        hasItems = true;
                        html += '<li>' + getInMenuButtonHtml(options.customButtons[i].button, data, type, row) + '</li>';
                    }
                }

                html += '</ul></div>';
                if (hasItems == false) {
                    html = '';
                }
            }

            return html;

        }

        function getInColumnButtonHtml(button, data, type, row) {
            var columnData = extractAllButtonData(button.columnData, row, button.data);
            if(button.buttonType == grid.ButtonTypes.Enable && row.isActive!=undefined){
                if(row.isActive==true){
                    button.type="danger";
                }
                else{
                    button.type="success";
                }
            }
            var html = '<a class="btn btn-circle btn-xs btn-' + button.type + '"';
            html += " data-params='" + JSON.stringify(columnData) + "' ";
            html += " data-url='" + button.url + "' ";
            if (button.onclick) {
                html += " onclick='" + button.onclick + "' ";
                if (button.icon) {
                    html += "title='"+button.text+"' ";
                }
            }
            html += ">";
            if (button.icon) {
                if(button.buttonType == grid.ButtonTypes.Enable && row.isActive!=undefined){
                    if(row.isActive==true){
                        html += "<span class='glyphicon glyphicon-remove'></span>";
                    }
                    else{
                        html += "<span class='glyphicon glyphicon-ok'></span>";
                    }
                }
                else
                html += "<span class='glyphicon glyphicon-" + button.icon + "'></span>";
            }
            html += "</a>";
            button.rendered = true;
            return html;
        }

        function getInMenuButtonHtml(button, data, type, row) {
            var columnData = extractAllButtonData(button.columnData, row, button.data);
            var html = '<a';
            html += " data-params='" + JSON.stringify(columnData) + "' ";
            html += " data-url='" + button.url + "' ";
            if (button.onclick) {
                html += " onclick='" + button.onclick + "' ";
            }
            html += ">";
            if (button.icon) {
                if(button.buttonType == grid.ButtonTypes.Enable && row.isActive!=undefined){
                    if(row.isActive==true){
                        html += "<span class='glyphicon glyphicon-remove'></span>&nbsp; Pasif Yap";
                    }
                    else{
                        html += "<span class='glyphicon glyphicon-ok'></span>&nbsp; Aktif Yap";
                    }
                }
                else
                html += "<span class='glyphicon glyphicon-" + button.icon + "'></span>&nbsp;";
            }
            if (button.text && button.buttonType != grid.ButtonTypes.Enable) {
                html += button.text;
            }
            html += "</a>";
            return html;
        }

        function getHeaderButtonHtml(button, data, type, row) {
            return getInMenuButtonHtml(button, data, type, row);
        }

        function extractAllButtonData(columnDataArr, row, additionalData) {
            var data = {};
            if (columnDataArr) {
                for (var i = 0; i < columnDataArr.length; i++) {
                    for (key in row) {
                        if (key == columnDataArr[i]) {
                            var val = '{"' + key.toString() + '":"' + row[key] + '"}';
                            $.extend(data, JSON.parse(val));
                            break;
                        }
                    }
                }
            }
            if (additionalData) {
                $.extend(data, additionalData);
            }
            return data;
        }
    };

    grid.Button = function (buttonType, data, url, columnData, text, onclick, icon, type, includeInMenu, showInHeader) {
        this.showInHeader = showInHeader;
        this.type = type;
        this.icon = icon;
        this.data = data;
        this.columnData = columnData;
        this.url = url;
        this.text = text;
        this.onclick = onclick;
        this.buttonType=buttonType;
        this.includeInMenu = includeInMenu;
        if (buttonType == grid.ButtonTypes.Create) {
            if (!icon) {
                this.icon = "plus";
            }
            if (!type) {
                this.type = "info";
            }
            if (!showInHeader) {
                this.showInHeader = true;
                this.includeInMenu = false;
                this.columnData = null;
            }
        }
        if (buttonType == grid.ButtonTypes.Update) {
            if (!icon) {
                this.icon = "pencil";
            }
            if (!type) {
                this.type = "success";
            }
            this.showInHeader = false;
            this.includeInMenu = true;
        }

        if (buttonType == grid.ButtonTypes.Delete) {
            if (!icon) {
                this.icon = "remove";
            }
            if (!type) {
                this.type = "danger";
            }
            this.showInHeader = false;
            this.includeInMenu = true;
        }
        if (buttonType == grid.ButtonTypes.Enable) {
            if (!icon) {
                this.icon = "ok";
            }
            if (!type) {
                this.type = "success";
            }
            this.showInHeader = false;
            this.includeInMenu = true;
        }
        if (buttonType == grid.ButtonTypes.Custom) {
            if (showInHeader == true) {
                this.columnData = null;
                this.includeInMenu = false;
            }
        }
        return this;
    };

    grid.ButtonTypes = {
        Create: 1,
        Update: 2,
        Delete: 3,
        Enable: 4,
        Custom: 5
    }


})(KS.Grid);