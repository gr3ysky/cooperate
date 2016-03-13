(function (grid) {
    grid.Render = function (selector, url, options) {
        $(selector).addClass("table table-striped table-bordered table-hover table-responsive");
        var defaults = {
            ajax: {
                url: url,
                dataSrc: "data",
                type: "POST",
                serverSide: true,
                processing: true,
                data: getData,
            },
            minCustomButtonCountForMenu: 2,
            deferRender: true,
            columnDefs: getColumnDefs()
        };
        $.extend(defaults, options);

        $(selector).dataTable(defaults);


//console.log(options.customButtons);

        if (options.customButtons) {

        }

        function getColumnDefs() {
            console.log("getColumnDefs");
            if (options.customButtons) {
                return [
                    {
                        render: function (data, type, row) {
                            var buttonscount = getCustomButtonsCount(options);
                            var html = "";
                            if (buttonscount > defaults.minCustomButtonCountForMenu)
                                html = getCustomButtonsMenuHtml(options, data, type, row);
                            else html = getCustomButtonsColumnsHtml(options, data, type, row)//getCustomButtonsHtml(options,data,type,row);
                            return html;
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
            if (options.filterForm) {
                var $form = $("#" + options.filterForm);
                $.extend(d, $form.serialize());
            }
            $.extend(d, {"_csrf": $("input[name='_csrf']").val()});
            return d;
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
            var html = '<a class="btn btn-circle btn-xs btn-' + button.type + '"';
            html += " data-params='" + JSON.stringify(columnData) + "' ";
            html += " data-url='" + button.url + "' ";
            if (button.onclick) {
                html += " onclick='" + button.onclick + "' ";
            }
            html += ">";
            if (button.icon) {
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
                html += "<span class=glyphicon glyphicon-" + button.icon + "'></span>";
            }
            if (button.text) {
                html += button.text;
            }
            html += "</a>";
            button.rendered = true;
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