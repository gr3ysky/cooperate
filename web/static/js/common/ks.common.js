/// <reference path="ks.base.js" />
/// <reference path="~/Scripts/toastr.js" />
(function (ks) {
    ks.Ajax = {};
    ks.Ajax.Post = function (url, data, onSuccess, onComplete, btn) {
        if (btn) {
            ks.Ajax.AddLoading(btn);
            $('#shader').fadeIn();
        }
        var isFormData = false;
        if (data instanceof FormData) {
            isFormData = true;
            ctype = false;
        }


        $.ajax(url, {
            beforeSend: function (xhr) {
                xhr.setRequestHeader('Accept', 'application/json');
                // xhr.setRequestHeader(  'Content-Type', 'application/json' );
                xhr.setRequestHeader("X-CSRF-TOKEN", $("input[name='_csrf']").val());
            },
            processData: isFormData == true ? false : true,
            data: data,
            global: true,
            type: 'POST',
            dataType: "json",
            contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
            cache: false,
            error: function (jqXHR, textStatus, errorThrown) {
                var json = {};
                try {
                    json = JSON.parse(jqXHR.responseText);
                } catch (e) {
                    if (jqXHR.status == 404) {
                        json.message = "İçerik bulunamadı";
                    }
                    else {
                        json.message = "Bir hata oluştu";
                    }

                }
                KS.Message.Error(json.message, "Hata");
            },
            success: function (e) {
                if (e.message && e.status) {
                    if (e.status == "success")
                        KS.Message.Success(e.message);
                    else {
                        KS.Message.Error(e.message);
                    }
                }
                if (onSuccess) {
                    onSuccess(e);
                }
            },
            complete: function () {
                if (btn) {
                    ks.Ajax.RemoveLoading(btn);
                    $('#shader').fadeOut();
                }
                if (onComplete)
                    onComplete();
            }
        });
    };

    ks.Ajax.Get = function (url, data, onSuccess, onComplete, btn) {
        $('#shader').fadeIn();
        if (btn)
            ks.Ajax.AddLoading(btn);
        $.ajax(url, {
            data: data,
            global: true,
            type: 'GET',
            cache: false,
            dataType: "html",
            error: function (jqXHR, textStatus, errorThrown) {

                var json = "";
                try {
                    json = JSON.parse(jqXHR.responseText);
                } catch (e) {

                }
                //console.log(json);
                if (json != "") {
                    KS.Message.Error(json.message);
                } else if (jqXHR.status == 500) {
                    KS.Message.Error(JSON.parse(jqXHR.responseText).message);
                } else if (jqXHR.status == 404) {
                    KS.Message.Error("İçerik bulunamadı.");
                }
            },
            success: function (html) {
                onSuccess(html);
            },
            complete: function () {
                $('#shader').fadeOut();
                if (btn)
                    ks.Ajax.RemoveLoading(btn);
                if (onComplete)
                    onComplete();
            }
        });
    };

    ks.Ajax.AddLoading = function (obj) {
        var $obj = $(obj);
        //if (!$obj.is("button")) return;
        $("i", $obj).hide();
        $obj.prepend("<i class='fa fa-refresh fa-spin'></i>");
        obj.disabled = true;

    };
    ;
    ks.Ajax.RemoveLoading = function (obj) {
        var $obj = $(obj);
        //if (!$obj.is("button")) return;
        $(".fa.fa-refresh.fa-spin", $obj).remove();
        $("i", $obj).show();
        obj.disabled = false;
    }

}(KS));


/// <reference path="../toastr.js" />
(function (ks) {
    ks.PageMessage = function (type, message) {
        $("#page-message-wrap").html("").removeClass("not-empty").removeClass("empty").removeClass("hide");
        var closeButtonHtml = "<button type='button' class='close' data-dismiss='alert' aria-label='Close'><span aria-hidden='true'>×</span></button>";
        var html = "<div class='alert alert-" + type + " alert-dismissible fade in'>" + closeButtonHtml + message + "</div>";
        $("#page-message-wrap").html(html).addClass("not-empty");

        window.setTimeout(function () {
            $("#page-message-wrap .alert").slideUp(300, "linear", function () {
                ks.PageMessage.Clear();
            });
        }, 4000);
    };
    ;

    ks.PageMessage.Clear = function () {
        $("#page-message-wrap").html("").removeClass("not-empty").addClass("empty").addClass("hide");
    }
}(KS));


(function (ks) {
    var toastrOptions = {
        positionClass: "toast-top-right",
        "preventDuplicates": true
    };
    ;
    ks.Success = function (message, title, options) {
        toastr.success(message, title, extendedOptions(options));
    };
    ;

    ks.Warning = function (message, title, options) {
        toastr.warning(message, title, extendedOptions(options));
    };
    ;

    ks.Info = function (message, title, options) {
        toastr.info(message, title, extendedOptions(options));
    };
    ;

    ks.Error = function (message, title, options) {
        toastr.error(message, title, extendedOptions(options));
    };
    ;

    function extendedOptions(options) {
        if (options) {
            $.extend(toastrOptions, HTMLOptionsCollection);
        }
        return toastrOptions;
    }
})(KS.Message);

//Common Functions

(function (ks) {

    ks.CloseAjaxContent = function () {
        $("#loaded-content").fadeOut(function () {
            $("#loaded-content").empty();
            $("#page-content").fadeIn();
        });
    };

    ks.ConfirmCloseAjaxContent = function () {
        KS.Common.PopupConfirm('Kapatmak istediğinize emin misiniz?', "Onay", function () {
            ks.CloseAjaxContent();
        });
    };


    ks.QueryStringToJson = function (str) {
        if (str === '') return {};
        var data = {};
        var item;
        if (str.indexOf('&') > 0) {
            var pairs = str.split('&');
            for (var pair in pairs) {
                if (pairs.hasOwnProperty(pair)) {
                    item = pair.split('=');
                    if (item[0]) {
                        data['' + item[0] + ''] = decodeURIComponent(item[1]);
                    }
                }
            }
        }
        else {
            item = str.split('=');
            if (item[0]) {
                data['' + item[0] + ''] = decodeURIComponent(item[1]);
            }
        }
        return data;

    };
    ;

    ks.PopupConfirm = function (message, title, confirmFunc) {

        KS.Confirm({
            message: message,
            title: title,
            confirmFunc: confirmFunc

        });
    };
    ;
    ks.ParseDate = function (strDate) {
        if (strDate) {
            var from = strDate.split(".");
            f = new Date(from[2].substring(0, 4), from[1] - 1, from[0]);
            return f;
        }
    };
    ;
    ks.FormatDateToString = function (date) {
        var dd = date.getDate();
        var mm = date.getMonth() + 1; //January is 0!
        var yyyy = date.getFullYear();

        if (dd < 10) {
            dd = '0' + dd
        }

        if (mm < 10) {
            mm = '0' + mm
        }

        return dd + '.' + mm + '.' + yyyy;

    };
    ks.InitPage = function () {


        $("select[multiple]").chosen({width: '100%'});
        // $('#jqxNumberInput').jqxNumberInput({ max:99999.99, min:0 });
        // $(".numericInput").each(function (i, e) {
        //     console.log(e);
        //     //The field must be a number hatası için silindi.
        //  //   $(e).removeAttr("data-val-number");
        //   var opts = { decimalSeparator: ".", decimalDigits: 2, spinMode: 'simple' };
        //     var min=$(e).data("val-range-min");
        //     if (min) {
        //         $.extend(opts, { min: min });

        //     }
        //     var max=$(e).data("val-range-max");
        //     if (max){
        //         $.extend(opts, { max: max });
        //     }

        //     $(e).jqxNumberInput(opts);
        //});
    }

})(KS.Common);

(function (url) {
    url.Base = "/"; //TODO set inside layout -> KS.Url.Base ='@Url.Content("~")';

    url.Action = function (controller, action, area, params) {
        var uri = '';

        if (area)
            uri = url.Base + area + "/" + controller + "/" + action;
        else {
            uri = url.Base + controller + "/" + action;
        }
        if (params) {
            uri += "?" + params;
        }
        return uri;
    };

})(KS.Url);

(function ($) {
    $.fn.ksAutoComplete = function (url, controlId, options) {
        if (url == '')
            return;
        var defaults = {
            minLength: 3,
            dataType: "POST",
            valueField: "Value",
            textField: "Text",
            loadingImage: '../Content/Images/loading.gif',
            data: {},
            waitTime: 1000,
            resultsBgColor: '#fff',
            resultsBorderColor: ' #c0c0c0',
            resultsMaxHeigth: "120px",
            afterSelect: function () {
            }
        };
        var $this = $(this);
        var isWaiting = false;
        $.extend(true, defaults, options);
        if ($this.is("input[type=text]") == false) {
            alert("Wrong input type");
            return;
        }

        $this.width($this.width() - 24);

        var wrap = "<div id='" + $this.attr("id") + "-autoCompleteResultsWrap' class='autoCompleteWrap'style='display:inline:block; position:relative;' />";
        var timeOut;
        $this.wrap(wrap);
        $this.attr("autocomplete", "off");
        if (defaults.loadingImage != '')
            $this.closest('.autoCompleteWrap').append("<img class='autoCompleteLoading' style='width:16px; display:none;' src='" + defaults.loadingImage + "'/>");
        $this.bind("keyup", "", function () {
            var length = $this.val().length;
            if (length >= defaults.minLength) {
                isWaiting = true;
                timeOut = setTimeout(function () {
                    search();
                }, defaults.waitTime);
            } else {
                removeResultsWrap();
            }
        });


        function removeResultsWrap() {
            var $resultWrap = $(".autoCompleteResultsWrap", $this.parents('.autoCompleteWrap').eq(0));
            $resultWrap.remove();
            $this.next('.autoCompleteLoading').hide();
        }

        function search() {
            if (isWaiting == false) {
                clearTimeout(timeOut);
                return;
            }
            removeResultsWrap();
            isWaiting = false;
            $.extend(defaults.data, {searchText: $this.val()});
            $this.next('.autoCompleteLoading').show();
            var request = $.ajax({
                url: url,
                type: defaults.dataType,
                data: defaults.data,
                dataType: "json"
            });
            request.success(function (json) {
                $this.next('.autoCompleteLoading').hide();
                showResults(json);
            });
            request.fail(function () {
                $this.next('.autoCompleteLoading').hide();
            });
        }

        function showResults(json) {
            prepareResultsWrapper(json);

        }

        function prepareResultsWrapper(json) {
            var html = "<div class='autoCompleteResultsWrap' style='position:absolute; display:none; padding:5px; z-index:1000; border-radius:2px;'>";
            var liHtml = "<ul class='autoCompleteResultList'>";
            $(json).each(function (i, e) {

                liHtml += "<li class='autoCompleteListItem' data-item-value='" + e[defaults.valueField] + "'><a class= 'k-link'>" + e[defaults.textField] + "</a></li>";
            });
            liHtml += "</ul>";
            html += liHtml + '</div>';
            $this.parents('.autoCompleteWrap').first().append(html);
            var $resultWrap = $(".autoCompleteResultsWrap", $this.parents('.autoCompleteWrap').eq(0));
            $resultWrap.css({
                "border": "1px solid" + defaults.resultsBorderColor,
                "background-color": defaults.resultsBgColor,
                "min-width": $this.width() + 24,
                "max-width": $this.width() + 100,
                "max-height": defaults.resultsMaxHeigth,
                "overflow": "auto"
            });
            $(".autoCompleteResultList").css({
                "padding": "0"
            });
            $(".autoCompleteListItem").css({
                "list-style": "none",
                "padding": "3px 8px",
                "border-bottom": "1px dotted" + defaults.resultsBorderColor
            });

            $(".autoCompleteListItem a").css({
                "display": "inline-block",
                "width": "100%",
                "cursor": ""
            });

            $(".autoCompleteListItem a", $resultWrap).click(function () {
                selectItem(this);
            });
            $(document).click(function (e) {
                if (e.target.id != $this.attr("id") + "-autoCompleteResultsWrap") {
                    removeResultsWrap();
                }
            });

            $resultWrap.slideDown();
        }

        function selectItem(e) {
            $this.val($(e).html());
            var control = $("#" + controlId);
            if (control.length == 0) {
                //add hidden for the contol
                var mvcId = controlId.replace("[", "_").replace("]", "_").replace(".", "_");
                var html = "<input type='hidden' name='" + controlId + "' id='" + mvcId + "' />";
                $this.closest('.autoCompleteWrap').append(html);
                control = $("#" + mvcId);
            }
            control.val($(e).parents("li").first().data("item-value"));
            var $resultWrap = $(".autoCompleteResultsWrap", $this.parents('.autoCompleteWrap').eq(0));
            $resultWrap.slideUp(function () {
                $(this).remove();
            });

        }
    };
}(jQuery));
