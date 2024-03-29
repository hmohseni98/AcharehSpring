function getDevice() {
    var $body = $("body");
    if (!$body.hasClass("wz-production")) return $body.attr("data-device");
    var width = $(window).outerWidth();
    return 768 > width ? "mobile" : 1170 > width ? "tablet" : "desktop"
}

function calculateFullWidthElement() {
    var $style = $("style#wz_full_width_element"), device = getDevice(), bodyWidth = "100vw";
    $style.length || ($style = $("<style>").appendTo($("head")), $style.attr("id", "wz_full_width_element"));
    var deviceWidth = 1170;
    "tablet" === device ? deviceWidth = 768 : "mobile" === device && (deviceWidth = 468);
    var css = ".wz-element.wz-element-full-width{width:" + bodyWidth + "; left: calc(-1 * ((" + bodyWidth + " - " + deviceWidth + "px) / 2))}";
    $style.html(css)
}

function setCookie(cname, cvalue, exdays) {
    var d = new Date;
    d.setTime(d.getTime() + 24 * exdays * 60 * 60 * 1e3);
    var expires = "expires=" + d.toUTCString();
    document.cookie = cname + "=" + cvalue + ";" + expires + ";path=/"
}

function getCookie(cname) {
    for (var name = cname + "=", ca = document.cookie.split(";"), i = 0; i < ca.length; i++) {
        for (var c = ca[i]; " " == c.charAt(0);) c = c.substring(1);
        if (0 == c.indexOf(name)) return c.substring(name.length, c.length)
    }
    return ""
}

function getParameterByName(name, url) {
    void 0 === url && (url = null), url || (url = window.location.href), name = name.replace(/[\[\]]/g, "\\$&");
    var regex = new RegExp("[?&]" + name + "(=([^&#]*)|&|#|$)"), results = regex.exec(url);
    return results ? results[2] ? decodeURIComponent(results[2].replace(/\+/g, " ")) : "" : null
}

function showAddressMap(coordinate) {
    if (void 0 === coordinate && (coordinate = null), $("#shop-address-map:visible").length > 0) {
        var $new = $("<div>").attr("id", "shop-address-map"), $old = $("#shop-address-map:visible");
        $new.insertAfter($old), $old.remove(), $.getScript("https://cdn.map.ir/web-sdk/1.4.2/js/mapp.env.js", function () {
            $.getScript("https://cdn.map.ir/web-sdk/1.4.2/js/mapp.min.js", function () {
                var latlng = {lat: 35.7, lng: 51.4}, zoom = 18;
                if (coordinate) {
                    var coordinates = coordinate.split(",");
                    latlng = {lat: coordinates[0], lng: coordinates[1]}, zoom = 18
                }
                var apiKey = mapIrApiKey, app = new Mapp({
                    element: "#shop-address-map",
                    presets: {latlng: latlng, zoom: zoom},
                    locale: "fa",
                    apiKey: apiKey
                });
                app.addVectorLayers(), app.addGeolocation({
                    pan: !0,
                    popup: !1,
                    onLoad: !coordinate,
                    onLoadCallback: function () {
                        if (!coordinate) {
                            app.findReverseGeocode({
                                before: function () {
                                }, after: function (data) {
                                    $.wzOnceTimeout("map_location", function () {
                                        var $address = $(".shop-map-wrapper").siblings().find("[name=address]");
                                        ($address.val().toString().length < 1 || selectedAddress == $address.val()) && $address.val(data.address_compact), selectedAddress = data.address_compact, $(".shop-map-wrapper").find("[name=coordinate]").val(data.geom.coordinates[1] + "," + data.geom.coordinates[0])
                                    })
                                }
                            })
                        }
                    }
                });
                var selectedAddress = "";
                app.map.on("dragend", function () {
                    app.findReverseGeocode({
                        before: function () {
                        }, after: function (data) {
                            $.wzOnceTimeout("map_location", function () {
                                var $address = $(".shop-map-wrapper").siblings().find("[name=address]");
                                ($address.val().toString().length < 1 || selectedAddress == $address.val()) && $address.val(data.address_compact), selectedAddress = data.address_compact, $(".shop-map-wrapper").find("[name=coordinate]").val(data.geom.coordinates[1] + "," + data.geom.coordinates[0])
                            })
                        }
                    })
                })
            })
        })
    }
}

var WidgetSetting = function () {
    function WidgetSetting() {
    }

    return WidgetSetting.getWidgetSetting = function (elementName, option, defaultValue) {
        return void 0 === defaultValue && (defaultValue = !1), WidgetSetting._settings.hasOwnProperty(elementName) && WidgetSetting._settings[elementName].hasOwnProperty(option) ? WidgetSetting._settings[elementName][option] : defaultValue
    }, WidgetSetting.setWidgetSetting = function (elementName, option, value) {
        WidgetSetting._settings.hasOwnProperty(elementName) || (WidgetSetting._settings[elementName] = {}), WidgetSetting._settings[elementName][option] = value
    }, WidgetSetting._settings = {}, WidgetSetting
}(), siteLoaded = !1;
"undefined" == typeof currency_sign && (currency_sign = " تومان"), "undefined" == typeof shop_product_without_default_options && (shop_product_without_default_options = !1), "undefined" == typeof next_label && (next_label = "بعدی"), "undefined" == typeof shop_cart_url && "undefined" != typeof site_url && (shop_cart_url = site_url + "shop/cart/"), "undefined" == typeof shop_product_hide_option && (shop_product_hide_option = !0), "undefined" == typeof shop_product_change_hash && (shop_product_change_hash = !0), "undefined" == typeof recent_product_page_size && (recent_product_page_size = 12), "undefined" == typeof recent_product_next_page && (recent_product_next_page = 2), "undefined" == typeof product_options && (product_options = []), function ($) {
    var $window = $(window), $body = $("body"), time_out_queue = [];
    $.wz_cache = {};
    var $loader = $('<div class="wz-loader-container"><div class="sk-circle"><div class="sk-circle1 sk-child"></div><div class="sk-circle2 sk-child"></div><div class="sk-circle3 sk-child"></div><div class="sk-circle4 sk-child"></div><div class="sk-circle5 sk-child"></div><div class="sk-circle6 sk-child"></div><div class="sk-circle7 sk-child"></div><div class="sk-circle8 sk-child"></div><div class="sk-circle9 sk-child"></div><div class="sk-circle10 sk-child"></div><div class="sk-circle11 sk-child"></div><div class="sk-circle12 sk-child"></div></div></div>'),
        reffer = null;
    if (document.referrer) var reffer_1 = document.referrer.match(/:\/\/(.[^/]+)/)[1];
    var utm_source = getParameterByName("utm_source") || reffer, utm_medium = getParameterByName("utm_medium"),
        utm_campaign = getParameterByName("utm_campaign"), utm_term = getParameterByName("utm_term"),
        utm_content = getParameterByName("utm_content");
    if (utm_source || utm_medium || utm_campaign) {
        var utm = [];
        utm_source && utm.push("utm_source=" + utm_source), utm_medium && utm.push("utm_medium=" + utm_medium), utm_campaign && utm.push("utm_campaign=" + utm_campaign), utm_term && utm.push("utm_term=" + utm_term), utm_content && utm.push("utm_content=" + utm_content), utm = utm.join("&"), getCookie("first_utm") || setCookie("first_utm", utm, 30), setCookie("last_utm", utm, 30)
    }
    "takhfifan" === getParameterByName("utm_source", window.location.href) && getParameterByName("tatoken", window.location.href) && setCookie("tatoken", getParameterByName("tatoken", window.location.href), 30), function (String) {
        String.prototype.formatNumber = function () {
            var n = this.toString().split(".");
            return n[0] = n[0].replace(/\B(?=(\d{3})+(?!\d))/g, ","), n.join(".")
        }, String.prototype.convertToLocalNumber = function () {
            var id = ["۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹"];
            return this.toString().replace(/[0-9]/g, function (w) {
                return id[+w]
            })
        }, String.prototype.convertToLatinNumber = function () {
            return this.replace(/[\u0660-\u0669]/g, function (c) {
                return c.charCodeAt(0) - 1632
            }).replace(/[\u06f0-\u06f9]/g, function (c) {
                return c.charCodeAt(0) - 1776
            })
        }, String.prototype.slugify = function (removeSlash) {
            return void 0 === removeSlash && (removeSlash = !0), this.toString().trim().replace(new RegExp("[^؀-ۿ\\uFB8Aپچگa-z0-9-" + (removeSlash ? "" : "/") + "]", "gi"), "-").replace(/-+/g, "-").replace(/^-|-$/g, "")
        }, String.prototype.thumb = function (width, height, resize) {
            return void 0 === resize && (resize = "k"), -1 === this.indexOf(upload_url) ? this : (-1 === ["s", "k", "c"].indexOf(resize) && (resize = "k"), this.replace(RegExp("(.[a-zA-z0-9]+$)", "g"), function (match) {
                return ".w_" + Math.round(width) + ",h_" + Math.round(height) + ",r_" + resize + match
            }))
        }
    }(String), $(function () {
        $.getUrlQueries = function () {
            var queries = {};
            return $.each(document.location.search.substr(1).split("&"), function (c, q) {
                var i = q.split("=");
                queries[i[0].toString()] = i[1].toString()
            }), queries
        }, $.wzOnceTimeout = function (name, callback, time) {
            void 0 === time && (time = 1500), clearTimeout(time_out_queue[name]), time_out_queue[name] = setTimeout(callback, time)
        }, $.wzUuid = function () {
            function s4() {
                return Math.floor(65536 * (1 + Math.random())).toString(16).substring(1)
            }

            return s4() + s4() + "-" + s4() + "-" + s4() + "-" + s4() + "-" + s4() + s4() + s4()
        }, $.wzConvertToLocalNumber = function (num) {
            return num.toString().convertToLocalNumber()
        }, $.wzGetFilterPrice = function (from, to) {
            return $.wzConvertToLocalNumber(Math.round(from)).toString().formatNumber() + currency_sign + " - " + $.wzConvertToLocalNumber(Math.round(to).toString().formatNumber()) + currency_sign
        }, $.wzUpdateShopList = function () {
            var $filterContainer = $(".wz-shop-filter"),
                $recentList = $('[data-widget="wz-shop-recent"] .wz-shop-recent:not(.wz-recent-partial)'),
                $element = $recentList.parents('[data-widget="wz-shop-recent"]'),
                $options = $filterContainer.find('[type="checkbox"]'), $sort = $element.find(".wz-shop-sort select"),
                $perPage = $element.find(".wz-shop-per-page select"), $price = $(".wz-shop-price-slider"),
                price = $price.data("price"), $loader = $(".wz-loader-container.wz-loader-container-fix");
            $loader.show();
            var newList = [];
            $options.filter(":checked").each(function () {
                var $filter = $(this);
                newList.push($filter.attr("name") + "=" + $filter.val())
            });
            var search = decodeURIComponent(window.location.search.replace(new RegExp("^(?:.*[&\\?]" + encodeURIComponent("q").replace(/[\.\+\*]/g, "\\$&") + "(?:\\=([^&]*))?)?.*$", "i"), "$1"));
            if (WebziValidate.isEmpty(search) || newList.push("q=" + search), history.pushState) {
                newList.push("sort=" + ($sort.val() || "newest")), $perPage.length && $perPage.val() != $perPage.children("option").first().val() && newList.push("pageSize=" + $perPage.val()), price && (newList.push("price[min]=" + price.from), newList.push("price[max]=" + price.to));
                var filterQuery = newList.join("&"),
                    search_1 = +filterQuery.toString().length > 0 ? "?" + filterQuery : "",
                    pageSize_1 = recent_product_page_size;
                $perPage.length && (pageSize_1 = $perPage.val());
                var url = window.location.protocol + "//" + window.location.host + window.location.pathname + search_1;
                window.history.pushState({path: url}, "", url), "undefined" == typeof recent_product_show_attributes && (recent_product_show_attributes = !1);
                var apiUrl = shop_recent_api_url + search_1 + ("&withFilter=true&" + ("undefined" != typeof shop_category_page_id ? "category=" + shop_category_page_id : "")) + (recent_product_show_attributes ? "&showAttributes=true" : "") + (-1 == search_1.indexOf("pageSize") ? "&pageSize=" + pageSize_1 : "");
                $.getJSON(apiUrl, function (result) {
                    if (result.hasOwnProperty("items")) {
                        var top_1 = $recentList.position().top;
                        $(window).scrollTop() > top_1 && $("html, body").animate({scrollTop: top_1}, 250), setTimeout(function () {
                            $recentList.html("");
                            var template = $("template#shop-product-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                                compiled = _.template(template);
                            if (result.items.length > 0 ? $.each(result.items, function (index, product) {
                                $recentList.append(compiled({product: product}))
                            }) : $("<div>").addClass("wz-shop-recent-empty").text("هیچ محصولی یافت نشد.").appendTo($recentList), Number(result.count) > pageSize_1) {
                                for (var template_1 = $("template#shop-recent-pagination-button-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"), compiled_1 = _.template(template_1), baseUrl = $("link[rel='canonical']").text(), $ul = $("<ul>").addClass("wz-pagination"), numPages = Math.ceil(Number(result.count) / pageSize_1), i = 1; numPages >= i; i++) if (10 >= i) {
                                    var $li = $("<li>").appendTo($ul);
                                    $li.html(compiled_1({
                                        page: {
                                            num: i.toString().formatNumber().convertToLocalNumber(),
                                            url: baseUrl + search_1 + (-1 === search_1.indexOf("?") ? "?" : "&") + "page=" + i
                                        }
                                    }))
                                }
                                if (numPages > 1) {
                                    var $li = $("<li>").appendTo($ul);
                                    $li.html(compiled_1({
                                        page: {
                                            num: next_label,
                                            url: baseUrl + search_1 + (-1 === search_1.indexOf("?") ? "?" : "&") + "page=2"
                                        }
                                    }))
                                }
                                $ul.children().first().addClass("active"), $recentList.append($ul)
                            }
                            var elementHeight = $element.height();
                            $element.css("height", "auto");
                            var height = $element.height();
                            $element.css("height", elementHeight), $element.cascadeHeight(height - elementHeight)
                        }, 300)
                    }
                    if (result.hasOwnProperty("filter") && $("#shop-product-filter-option").length) {
                        var filterTemplate = $("#shop-product-filter-option").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                            categoryTemplate = $("#shop-product-filter-category").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                            filterCompiled = _.template(filterTemplate),
                            categoryCompiled = _.template(categoryTemplate);
                        $filterContainer.children("li.wz-shop-filter-item").not(".wz-shop-filter-price, .wz-shop-filter-category").remove();
                        var $categoryContainer = $filterContainer.find(".wz-shop-filter-category ul");
                        $categoryContainer.length && $categoryContainer.html(categoryCompiled({filter: result.filter})), $filterContainer.append(filterCompiled({filter: result.filter})), $options = $filterContainer.find('[type="checkbox"]'), $.renderProductFilter()
                    }
                    setTimeout(function () {
                        $loader.hide()
                    }, 100)
                })
            }
        }, $.renderProductFilter = function () {
            var $filterContainer = $(".wz-shop-filter"),
                $sort = $('[data-widget="wz-shop-recent"] .wz-shop-sort select'),
                $perPge = $('[data-widget="wz-shop-recent"] .wz-shop-per-page select'),
                selectedList = document.location.search.replace("?", "").split("&");
            $.each(selectedList, function (indexInArray, valueOfElement) {
                valueOfElement = decodeURIComponent(valueOfElement);
                var index = valueOfElement.lastIndexOf("=");
                if (-1 !== index) {
                    var filterName_1 = valueOfElement.slice(0, index), filterValue_1 = valueOfElement.slice(index + 1);
                    if ("sort" === filterName_1) $sort.val(filterValue_1); else if ("pageSize" === filterName_1) $perPge.val(filterValue_1); else if ("price[min]" === filterName_1 || "price[max]" === filterName_1) setTimeout(function () {
                        var $slider = $(".wz-shop-price-slider"), slider = $slider.find("input").data("ionRangeSlider");
                        if (slider) {
                            var max = Number($slider.attr("data-max-price")),
                                min = Number($slider.attr("data-min-price")), value = Number(filterValue_1);
                            if ("price[min]" === filterName_1 && (min > value && (value = min), value > max && (value = min), slider.update({from: value})), "price[max]" === filterName_1) {
                                var min_1 = $slider.attr("data-min-price");
                                min_1 > value && (value = max), value > max && (value = max), slider.update({to: value})
                            }
                        }
                    }, 100); else {
                        var nameArray = filterName_1.replace("]", "").split("[", 2);
                        if (nameArray[0] && nameArray[1]) {
                            var name_1 = nameArray[0] + "[" + nameArray[1] + "]",
                                $checkbox = $filterContainer.find("input[type=checkbox][name*='" + name_1 + "'][value='" + filterValue_1 + "']");
                            $checkbox.parents(".dropdown").addClass("open"), $checkbox.prop("checked", !0)
                        }
                    }
                }
            })
        }, $.getSelectedCombination = function (sellerId) {
            void 0 === sellerId && (sellerId = null);
            var selectedCombination = null,
                $options = $(".wz-shop-product-page .wz-shop-product-options .wz-shop-product-option");
            sellerId && ($options = $options.not(".wz-shop-product-sellers"));
            var options = [];
            $options.find("input:checked, select").each(function () {
                var $value = $(this);
                options.push($value.val())
            }), null !== sellerId && options.push(sellerId), options = options.sort(function (a, b) {
                return a - b
            });
            var optionsList = options.join();
            return "object" == typeof product_combinations && product_combinations.length > 0 && $.each(product_combinations, function (index, combination) {
                var combinationOptions = combination.optionsId.sort(function (a, b) {
                    return a - b
                });
                return combinationOptions.join() === optionsList ? (selectedCombination = combination, !1) : void 0
            }), selectedCombination ? selectedCombination : {optionsId: []}
        }, $.getProductFieldValues = function () {
            var $options = $(".wz-shop-product-page .wz-shop-product-option-field"), inputs = !1;
            return $options.length > 0 && (inputs = {}, $options.each(function () {
                var $input = $(this).find("textarea, input"), value = $input.val();
                value.toString().length > 0 && (inputs[$input.attr("id").replace("product-field-", "")] = value)
            })), inputs
        }, $.fn.hasFontSize = function () {
            return $(this).filter(function () {
                var style = $(this).attr("style");
                return /font-size:[^;]+px/.test(style)
            })
        }, $.changTextFontsSize = function () {
            var device = getDevice();
            $("[data-text-change-size]").each(function () {
                var $textContainer = $(this),
                    changeSize = parseFloat($textContainer.attr("data-" + device + "-text-size")) || 0,
                    $spanList = $textContainer.find("span, p, h1, h2 ,h3 ,h4, h5, h6").hasFontSize();
                $spanList.each(function () {
                    var $span = $(this), fontSize = $span.attr("desktop-font-size");
                    fontSize || (fontSize = parseFloat($span.css("font-size")), $span.attr("desktop-font-size", fontSize)), $span.css("font-size", (1 + changeSize) * fontSize + "px")
                })
            })
        }, $.fn.hideOutOfStockOption = function () {
            var $this = $(this);
            $this.val();
            if ($(".wz-hide-out-of-stock-loading").length > 0) return !1;
            var $seller = $(".wz-shop-product-sellers input"), sellerList = [];
            if ($seller.each(function () {
                sellerList.push($(this).val())
            }), $this.addClass("wz-hide-out-of-stock-loading"), "object" == typeof product_combinations && product_combinations.length > 0) {
                var selectedCombination = $.getSelectedCombination(), selectedOptions_1 = selectedCombination.optionsId,
                    outOfStockList_1 = [], hideList_1 = [], visibleList_1 = [];
                $seller.length && (selectedOptions_1 = $.extend([], selectedOptions_1), sellerList.forEach(function (sellerId) {
                    var index = selectedOptions_1.indexOf(sellerId);
                    index > -1 && selectedOptions_1.splice(index, 1)
                })), $.each(product_combinations, function (index, combination) {
                    var ids = $.extend([], combination.optionsId);
                    sellerList.forEach(function (sellerId) {
                        var index = ids.indexOf(sellerId);
                        index > -1 && ids.splice(index, 1)
                    }), ids.sort(), selectedOptions_1.forEach(function (optionId) {
                        var list = [],
                            $list = $(".wz-shop-product-options [value=" + optionId + "], .wz-shop-product-options [data-id=" + optionId + "]").parents(".wz-shop-product-option").find("input[data-option-id], option");
                        $list.each(function () {
                            var anotherId = $(this).val();
                            anotherId != optionId && list.push(anotherId)
                        }), list.forEach(function (anotherId) {
                            var tempList = $.extend([], selectedOptions_1);
                            tempList[tempList.indexOf(optionId)] = anotherId, tempList.sort(), JSON.stringify(tempList) == JSON.stringify(ids) && ("1" != combination.visible ? hideList_1.push(".wz-shop-product-options [value=" + anotherId + "], .wz-shop-product-options [data-id=" + anotherId + "]") : "1" != combination.in_stock ? outOfStockList_1.push(".wz-shop-product-options [value=" + anotherId + "], .wz-shop-product-options [data-id=" + anotherId + "]") : visibleList_1.push(".wz-shop-product-options [value=" + anotherId + "], .wz-shop-product-options [data-id=" + anotherId + "]"))
                        })
                    })
                }), $(".wz-shop-product-option").find("li, .dropdown-menu [data-id]").removeClass("out-of-stock").removeClass("hide");
                var $totalOptions = $(".wz-shop-product-options .wz-shop-product-option:not(.wz-shop-product-sellers)"),
                    totalOptions = $totalOptions.length,
                    totalOptionValues = $totalOptions.find("input[data-option-id], option").length;
                (2 > totalOptions || totalOptionValues - hideList_1.length - outOfStockList_1.length != selectedOptions_1.length) && ($(hideList_1.join(",")).each(function () {
                    $(this).parents("li").first().addClass("hide"), $(this).filter("[data-id]").addClass("hide")
                }), $(outOfStockList_1.join(",")).each(function () {
                    $(this).parents("li").first().addClass("out-of-stock"), $(this).filter("[data-id]").addClass("out-of-stock")
                }), $(visibleList_1.join(",")).each(function () {
                    $(this).parents("li").first().removeClass("out-of-stock hide"), $(this).filter("[data-id]").removeClass("out-of-stock hide")
                })), $this.removeClass("wz-hide-out-of-stock-loading")
            }
        }, $.fn.renderProductOption = function () {
            $(this).each(function () {
                var $productPage = $(this), $element = $productPage.parents(".wz-element"),
                    $optionContainer = $productPage.find(".wz-shop-product-options"),
                    $options = $productPage.find(".wz-shop-product-options .wz-shop-product-option:not(.wz-shop-product-option-field)"),
                    lastSelectedValue = null;
                $productPage.find(".wz-shop-product-sellers").length > 0 && $productPage.find(".wz-shop-product-add-cart:not(.wz-shop-product-seller-add-cart), .wz-shop-product-price").hide();
                var calculatePrice = function () {
                    if ("object" != typeof product_combinations || !product_combinations.length) return !0;
                    var selectedValue = null;
                    $options.find("input:checked, select").each(function () {
                        var $value = $(this);
                        "undefined" != typeof product_option_images && product_option_images.hasOwnProperty("id") && $value.attr("data-option-id") == product_option_images.id && (selectedValue = $value.val())
                    });
                    var selectedCombination = $.getSelectedCombination(), $productContainer = $productPage;
                    if ($productContainer.find("[wz-data-product-price]").removeAttr("wz-data-product-price"), selectedCombination && (selectedCombination.id || shop_product_without_default_options)) {
                        var $price = $productContainer.find(".wz-shop-product-price:not(.wz-shop-seller-price)"),
                            $outStock = $productContainer.find(".wz-shop-product-out-stock");
                        if (selectedValue && "undefined" != typeof product_option_images && product_option_images.hasOwnProperty("id") && product_option_images.hasOwnProperty("values") && (!lastSelectedValue || lastSelectedValue != selectedValue) && (lastSelectedValue = selectedValue, product_option_images.values.hasOwnProperty(selectedValue))) {
                            var imageIds_1 = product_option_images.values[selectedValue];
                            setTimeout(function () {
                                var $slider = $(".wz-shop-product-page .wz-gallery-slider"),
                                    slider = $slider.data("flexslider"),
                                    $carousel = $(".wz-shop-product-page .wz-gallery-carousel"),
                                    carousel = $carousel.data("flexslider"),
                                    $galleryItems = $(".wz-shop-product-page .wz-gallery-slider .slides li:not(.clone)"),
                                    $carouselItems = $(".wz-shop-product-page .wz-gallery-carousel .slides li:not(.clone)"),
                                    hasCarousel = $carouselItems.length > 0,
                                    $imageTemplate = $("#shop-product-images-template"),
                                    node = $imageTemplate.prop("content"), $imageList = $(node).find("li");
                                $galleryItems.each(function () {
                                    slider.removeSlide($(this))
                                }), $carouselItems.each(function () {
                                    carousel.removeSlide($(this))
                                });
                                var tempList = [];
                                imageIds_1.forEach(function (imageId) {
                                    $imageList.filter("[data-image-id=" + imageId + "]").length && tempList.push(imageId)
                                }), imageIds_1 = tempList, Array.isArray(imageIds_1) && imageIds_1.length > 0 ? imageIds_1.forEach(function (imageId) {
                                    if (slider.addSlide($imageList.filter("[data-image-id=" + imageId + "]").clone()), hasCarousel) {
                                        var $carouselItem = $imageList.filter("[data-image-id=" + imageId + "]").clone().removeAttr("data-thumb").removeAttr("data-image-id");
                                        $carouselItem.click(function () {
                                            var $this = $(this);
                                            $slider.flexslider($this.index()), $(".wz-shop-product-page .wz-gallery-carousel .slides li").removeClass("flex-active-slide"), $this.addClass("flex-active-slide")
                                        }), carousel.addSlide($carouselItem)
                                    }
                                }) : $imageList.each(function () {
                                    if (slider.addSlide($(this).clone()), hasCarousel) {
                                        var $carouselItem = $(this).clone().removeAttr("data-thumb").removeAttr("data-image-id");
                                        $carouselItem.click(function () {
                                            var $this = $(this);
                                            $slider.flexslider($this.index()), $(".wz-shop-product-page .wz-gallery-carousel .slides li").removeClass("flex-active-slide"), $this.addClass("flex-active-slide")
                                        }), carousel.addSlide($carouselItem)
                                    }
                                }), carousel.removeClass("flex-vertical-centered flex-centered"), "vertical" === carousel.vars.direction ? ($carousel.children("li").css({marginBottom: carousel.vars.itemMargin || 0}), ($carousel.height() > carousel.viewport.height() || 1 === imageIds_1.length) && carousel.addClass("flex-vertical-centered")) : ($(window).trigger("resize"), 1 === carousel.pagingCount && carousel.addClass("flex-centered")), $(".zoomContainer").remove(), $carousel.find("li").first().addClass("flex-active-slide"), $slider.flexslider(0)
                            }, 100)
                        }
                        if (1 != selectedCombination.in_stock || 1 != selectedCombination.visible) $outStock.length || ($outStock = $("<div>").addClass("wz-shop-product-out-stock").insertAfter($price), $price.remove()), $outStock.text(product_out_of_stock), selectedCombination.hasOwnProperty("price_label") && $outStock.text(selectedCombination.price_label), $productContainer.addClass("wz-product-out-of-stock"), $productContainer.find("[wz-data-product-price]").removeAttr("wz-data-product-price"); else {
                            if ($productContainer.removeClass("wz-product-out-of-stock"), $price.length || ($price = $("<div>").addClass("wz-shop-product-price").insertAfter($outStock), $outStock.remove()), selectedCombination.sale_price ? ($price.html(""), $("<span>").attr("wz-data-product-price", selectedCombination.price.toString()).addClass("wz-shop-product-old-price ").text(selectedCombination.price.toString().formatNumber().convertToLocalNumber() + currency_sign).appendTo($price), $("<span>").attr("wz-data-product-price", selectedCombination.sale_price.toString()).addClass("wz-shop-product-sale-price").text(selectedCombination.sale_price.toString().formatNumber().convertToLocalNumber() + currency_sign).appendTo($price)) : $price.html($("<span>").attr("wz-data-product-price", selectedCombination.price.toString()).text(selectedCombination.price.toString().formatNumber().convertToLocalNumber() + currency_sign)), selectedCombination.hasOwnProperty("quantity_label")) {
                                var $quantityRemind = $productContainer.find(".wz-shop-quantity-remind");
                                $quantityRemind.length < 1 && ($quantityRemind = $("<div>").addClass("wz-shop-product-out-stock wz-shop-quantity-remind").insertAfter($price)), $quantityRemind.text(selectedCombination.quantity_label)
                            } else {
                                var $quantityRemind = $productContainer.find(".wz-shop-quantity-remind");
                                $quantityRemind.length > 0 && ($quantityRemind = $quantityRemind.remove())
                            }
                            $('[data-widget="wz-shop-product"]').calculatePriceFormula()
                        }
                        selectedCombination.hasOwnProperty("price_label") && ($outStock = $("<div>").addClass("wz-shop-product-out-stock").insertAfter($price), $price.remove(), $productContainer.find("[wz-data-product-price]").removeAttr("wz-data-product-price"), $outStock.text(selectedCombination.price_label)), shop_product_without_default_options && $outStock.text("برای محاسبه قیمت گزینه های مد نظر را انتخاب نمایید.");
                        var $sku = $productContainer.find(".wz-shop-product-sku");
                        selectedCombination.sku ? $sku.text(product_text_sku + ": " + selectedCombination.sku) : $sku.text("");
                        var elementHeight = $element.height();
                        $element.css("height", "auto");
                        var height = $element.height();
                        $element.css("height", elementHeight), $element.cascadeHeight(height - elementHeight)
                    }
                }, sellerCalculatePrice = function (seller) {
                    var selectedCombination = $.getSelectedCombination(seller),
                        $productContainer = $(".wz-shop-product-sellers .seller-name[id=" + seller + "] + .seller-price");
                    if (selectedCombination) {
                        var $price = $productContainer.find(".wz-shop-seller-price");
                        $price.length || ($price = $("<div>").addClass("wz-shop-product-price wz-shop-seller-price").appendTo($productContainer)), 1 == selectedCombination.in_stock && 1 == selectedCombination.visible ? ($productContainer.parents("li").removeClass("hide"), selectedCombination.sale_price ? ($price.html(""), $("<span>").attr("wz-data-product-price", selectedCombination.price.toString()).addClass("wz-shop-product-old-price").text(selectedCombination.price.toString().formatNumber().convertToLocalNumber() + currency_sign).appendTo($price), $("<span>").attr("wz-data-product-price", selectedCombination.sale_price.toString()).addClass("wz-shop-product-sale-price").text(selectedCombination.sale_price.toString().formatNumber().convertToLocalNumber() + currency_sign).appendTo($price)) : $price.html($("<span>").attr("wz-data-product-price", selectedCombination.price.toString()).text(selectedCombination.price.toString().formatNumber().convertToLocalNumber() + currency_sign)), $('[data-widget="wz-shop-product"]').calculatePriceFormula()) : $productContainer.parents("li").addClass("hide")
                    }
                };
                if ($body.hasClass("wz-production")) {
                    $options.find("input, select");
                    $options.find("input, select").on("change", function (event) {
                        var queryList = [];
                        $options.find("input:checked, select").each(function () {
                            var $input = $(this);
                            queryList.push($input.attr("name").slugify() + "-" + $input.val())
                        });
                        var query = queryList.join("/");
                        shop_product_change_hash && (document.location.hash = decodeURI(query)), shop_product_hide_option && $(this).hideOutOfStockOption(), $productPage.find(".wz-shop-product-sellers").length ? $(".wz-shop-product-sellers input").each(function () {
                            sellerCalculatePrice($(this).val())
                        }) : calculatePrice()
                    })
                }
                shop_product_without_default_options || $options.each(function () {
                    var $option = $(this),
                        checkedRadio = $optionContainer.find("[name=" + $option.attr("id") + "][type=radio]").first();
                    if (checkedRadio.prop("checked", !0), $option.hasClass("wz-shop-product-colors")) {
                        var label = checkedRadio.next().attr("data-title");
                        $option.find(".wz-shop-product-color-result").text(" : " + label)
                    }
                });
                var selectedList = document.location.hash.replace("#", "").split("/");
                if ($.each(selectedList, function (indexInArray, valueOfElement) {
                    var index = valueOfElement.lastIndexOf("-");
                    if (-1 !== index) {
                        var optionName = decodeURI(valueOfElement.slice(0, index)),
                            selectedValue = decodeURI(valueOfElement.slice(index + 1));
                        $("select[name=" + optionName + "]").val(selectedValue).parents(".dropdown.dropdown-list").wzDropDown(), $("input[type=radio][name=" + optionName + "][value=" + selectedValue + "]").prop("checked", !0)
                    }
                }), "object" == typeof product_combinations && product_combinations.length > 0 && shop_product_hide_option) {
                    var selectedCombination = $.getSelectedCombination();
                    if (!selectedCombination || "1" != selectedCombination.in_stock || "1" != selectedCombination.visible) {
                        var newCombination_1 = null;
                        $.each(product_combinations, function (index, combination) {
                            return "1" == combination.in_stock && "1" == combination.visible ? (newCombination_1 = combination, !1) : void 0
                        }), newCombination_1 && !shop_product_without_default_options && $.each(newCombination_1.optionsId, function (optionIndex, optionId) {
                            var $option = $(".wz-shop-product-options [value=" + optionId + "], .wz-shop-product-options  .dropdown-list [data-id=" + optionId + "]"),
                                $select = $option.parents(".wz-shop-product-option").find("select");
                            if ($select.length) {
                                $select.val(optionId);
                                var $options_1 = $select.parents(".wz-shop-product-option"),
                                    $first = $options_1.find("[data-id=" + optionId + "]");
                                $options_1.find(".selected").removeClass("selected"), $first.addClass("selected"), $options_1.find(".dropdown-select").text($first.text()), $options_1.find("select").val($first.attr("data-id")), $select.trigger("change")
                            }
                            $option.click()
                        })
                    }
                    shop_product_without_default_options || $('.wz-shop-product-options [type="radio"]:checked, .wz-shop-product-options select').first().trigger("change", {manual: !0}), shop_product_without_default_options && $(".wz-shop-product-options select").parents(".dropdown.dropdown-list").wzDropDown({hidden: "یک گزینه را انتخاب نمایید."})
                }
                $productPage.find(".wz-shop-product-sellers").length ? $(".wz-shop-product-sellers input").each(function () {
                    sellerCalculatePrice($(this).val())
                }) : calculatePrice(), shop_product_without_default_options = !1
            })
        }, $.fn.calculatePriceFormula = function () {
            var $this = $(this), $inputList = $this.find("input[wz-data-formula]"),
                $priceList = $this.find("[wz-data-product-price]");
            $priceList.each(function () {
                var $priceItem = $(this), price = $priceItem.attr("wz-data-product-price"), finalPrice = price;
                $inputList.each(function () {
                    var $input = $(this), value = $input.val(), formula = $input.attr("wz-data-formula");
                    if (value.trim().length > 0) {
                        var p = parseFloat(finalPrice), x = parseFloat(value);
                        /^[:\s\.\>\<\=\?0-9px*-+\/\(\)]{1,250}$/.test(formula) && (finalPrice = eval(formula))
                    }
                    finalPrice = Math.ceil(finalPrice), $priceItem.text(finalPrice.toString().formatNumber().convertToLocalNumber() + currency_sign)
                })
            })
        }, $.wzRenderShopFilterCategories = function (categories) {
            var $list = $("<uL>");
            return categories.forEach(function (item) {
                var $li = $("<li>").appendTo($list), $a = $("<a>").text(item.name).appendTo($li);
                if ($a.attr({href: item.url}), item.hasOwnProperty("items")) {
                    var $ul = $("<ul>").addClass("sub-list").appendTo($li);
                    $ul.html($.wzRenderShopFilterCategories(item.items))
                }
            }), $list.html()
        }, $.fn.wzDropDown = function (options) {
            return void 0 === options && (options = {}), $(this).each(function () {
                var $dropDown = $(this);
                console.log("xx");
                var dropDownOptions = $.extend(!0, {}, {direction: "rtl"}, options);
                console.log("xx"), console.log(options), console.log(dropDownOptions), $dropDown.addClass("dropdown dropdown-list dropdown-" + dropDownOptions.direction), $dropDown.attr("data-wz-toggle", "dropdown");
                var $select = $dropDown.find("select");
                $select.hide(), dropDownOptions.hasOwnProperty("hidden") && $("<option>").text(dropDownOptions.hidden).attr("selected", "selected").attr("hidden", "hidden").val("").prependTo($select);
                var $sortList = $("<div>").addClass("dropdown-menu "), selectOptions = {}, first = null;
                $select.find("option").each(function () {
                    var $option = $(this);
                    selectOptions[$option.val()] = $option.text(), first && $select.val() !== $option.val() || (first = $option.val());
                    var $a = $("<a>").attr("name", "drop-down-" + $option.val()).attr("data-id", $option.val()).text($option.text()).appendTo($sortList);
                    "hidden" === $option.attr("hidden") && $a.attr("hidden", "hidden")
                }), $select.nextAll().remove(), dropDownOptions.hasOwnProperty("hidden") && (first = ""), $("<a>").addClass("dropdown-select").text(selectOptions[first]).appendTo($dropDown), $sortList.appendTo($dropDown), $sortList.find("[name=drop-down-" + first + "]").addClass("selected")
            }), this
        }, $.fn.waitForImages = function (callback) {
            var $images = $(this).find("img"), length = $images.length, count = 0;
            $images.each(function () {
                var img = $(this).get(0);
                img.complete && length--
            }), length > 0 ? $images.on("load", function () {
                count++, length === count && callback.call(null)
            }) : callback.call(null)
        }, $.fn.attrDevice = function (attribute) {
            var $this = $(this), device = getDevice();
            return "mobile" === device ? $this.attr(attribute + "-mobile") : "tablet" === device ? $this.attr(attribute + "-tablet") : $this.attr(attribute);
        }, $.fn.webziGalleryRender = function () {
            $(this).each(function () {
                var $this = $(this), type = $this.hasClass("wz-gallery-masonry") ? "masonry" : "grid",
                    $element = $this.parents(".wz-element").first(), columns = $element.attrDevice("data-columns") || 1,
                    imageCount = ($element.attrDevice("data-rows") || 1, $element.attrDevice("data-grid-size") || 9),
                    gridLayout = $element.attrDevice("data-grid-layout") || $element.attr("data-grid-layout") || "normnal";
                if ($this.attr("data-layout", gridLayout), "grid" === type) {
                    var $list = $this.children("li");
                    $list.removeClass("hide"), $list.length > imageCount ? ($this.css("height", "calc(100% - 25px)"), $element.find(".more-image").addClass("show"), $this.children("li:gt(" + (imageCount - 1) + ")").addClass("hide")) : ($this.height("inherit"), $element.find(".more-image").removeClass("show"))
                } else "masonry" === type && $this.css("column-count", columns)
            })
        }, $.fn.wzCountDown = function (options) {
            $(this).each(function () {
                function strfobj(str) {
                    var parsed = str.match(parser), obj = {};
                    return parts.forEach(function (label, i) {
                        obj[label] = parsed[i]
                    }), obj
                }

                function diff(obj1, obj2) {
                    var diff = [];
                    return parts.forEach(function (key) {
                        obj1[key] !== obj2[key] && diff.push(key)
                    }), diff
                }

                var $element = $(this), $container = $("<div>"), parts = ["week", "day", "hour", "minute", "second"],
                    dateFormats = {week: "%w", day: "%D", hour: "%H", minute: "%M", second: "%S"},
                    repeat = options.repeat || "never", now = (new Date).toLocaleString().split(/[, ]+|[\s]+/g),
                    nowDate = now[0] + " " + now[1], finalDate = options.endDate || nowDate, names = {
                        week: options.weekLabel || "Week",
                        day: options.dayLabel || "Day",
                        hour: options.hourLabel || "Hour",
                        minute: options.minuteLabel || "Minute",
                        second: options.secondLabel || "Second"
                    };
                if (options.hasOwnProperty("showNames") || (options.showNames = !0), options.hasOwnProperty("showWeek") || (options.showWeek = !0), options.hasOwnProperty("convertLocal") || (options.convertLocal = !0), options.hasOwnProperty("showDay") || (options.showDay = !0), options.hasOwnProperty("showSecond") || (options.showSecond = !0), options.showWeek && (dateFormats = {
                    week: "%w",
                    day: "%d",
                    hour: "%H",
                    minute: "%M",
                    second: "%S"
                }), options.style = options.style || "normal", "flip" === options.style ? $element.addClass("with-flip") : $element.addClass("normal"), "never" !== repeat) {
                    var dayMilliSeconds = 864e5, weekMilliSeconds = 7 * dayMilliSeconds, final = new Date(finalDate),
                        now_1 = new Date, periodMilliSeconds = dayMilliSeconds;
                    "everyDay" === repeat ? periodMilliSeconds = dayMilliSeconds : "everyWeek" === repeat && (periodMilliSeconds = weekMilliSeconds);
                    var distance = Number(now_1.getTime()) - Number(final.getTime());
                    if (distance > 0) {
                        var n = Math.floor(distance / periodMilliSeconds) + 1;
                        final = new Date(final.getTime() + n * periodMilliSeconds), finalDate = final.getFullYear() + "/" + (final.getMonth() + 1) + "/" + final.getDate() + " " + final.getHours() + ":" + final.getMinutes() + ":" + final.getSeconds()
                    }
                }
                parts.forEach(function (index) {
                    if (("week" !== index || options.showWeek) && ("second" !== index || options.showSecond) && ("day" !== index || options.showDay)) {
                        var $part = $('<div class="wz-countdown-part">').addClass(index).appendTo($container),
                            $value = $('<div class="wz-countdown-value">').appendTo($part);
                        "normal" === options.style ? ($('<div class="wz-countdown-text">').text(dateFormats[index]).appendTo($value), $('<div class="wz-countdown-colon">').text(":").appendTo($value)) : ($('<div class="count curr top">').text(dateFormats[index]).appendTo($value), $('<div class="count next top">').text(dateFormats[index]).appendTo($value), $('<div class="count next bottom">').text(dateFormats[index]).appendTo($value), $('<div class="count curr bottom">').text(dateFormats[index]).appendTo($value)), options.showNames && $('<div class="wz-countdown-name">').text(names[index]).appendTo($part)
                    }
                });
                var currDate = "00:00:00:00:00", nextDate = "00:00:00:00:00", parser = /([0-9]{2})/gi, first = !0;
                $element.countdown(finalDate, function (event) {
                    if (first && (options.convertLocal ? $element.html(event.strftime($container.html()).toString().convertToLocalNumber()) : $element.html(event.strftime($container.html())), first = !1), !options.review) if ("normal" === options.style) options.convertLocal ? $element.html(event.strftime($container.html()).toString().convertToLocalNumber()) : $element.html(event.strftime($container.html())); else {
                        var newDate = event.strftime("%w:%d:%H:%M:%S");
                        if (options.showWeek || (newDate = event.strftime("%w:%D:%H:%M:%S")), newDate !== nextDate) {
                            currDate = nextDate, nextDate = newDate;
                            var data_1 = {
                                curr: strfobj(currDate.toString().convertToLatinNumber()),
                                next: strfobj(nextDate.toString().convertToLatinNumber())
                            };
                            diff(data_1.curr, data_1.next).forEach(function (label) {
                                var selector = ".wz-countdown-part.%s".replace(/%s/, label),
                                    $node = $element.find(selector);
                                $node.removeClass("flip"), options.convertLocal ? ($node.find(".curr").text(data_1.curr[label].toString().convertToLocalNumber()), $node.find(".next").text(data_1.next[label].toString().convertToLocalNumber())) : ($node.find(".curr").text(data_1.curr[label]), $node.find(".next").text(data_1.next[label])), setTimeout(function ($node) {
                                    $node.addClass("flip")
                                }, 50, $node)
                            })
                        }
                    }
                })
            })
        }, $.fn.webziSliderRender = function (options) {
            var addedTime = null;
            $(this).each(function () {
                var $gallery = $(this), sliderConfig = $.extend(!0, {}, {
                    animation: "slide",
                    preloader: !0,
                    preloadTheseFrames: [1],
                    controlNav: !1,
                    slideshow: !1,
                    prevText: "",
                    nextText: "",
                    rtl: !0
                }, options);
                sliderConfig.hasOwnProperty("slideshowSpeed") && (sliderConfig.slideshowSpeed *= 1e3);
                var ezOptions = {
                    zoomWindowPosition: sliderConfig.rtl ? 11 : 1,
                    borderSize: 0,
                    easing: !0,
                    lensFadeIn: 500,
                    lensFadeOut: 500,
                    zoomType: "inner",
                    cursor: "crosshair"
                }, startFunction = null, afterFunction = null;
                sliderConfig.hasOwnProperty("start") && (startFunction = sliderConfig.start), sliderConfig.hasOwnProperty("after") && (startFunction = sliderConfig.after);
                var $animation = $gallery.find("[data-animation]");
                $body.hasClass("theme-editing") || $animation.each(function () {
                    "desktop" !== getDevice() && $animation.attr("data-" + getDevice() + "-animation") ? $animation.find(".wz-template").wzAnimationPlay({prefix: "data-" + getDevice() + "-animation"}) : $animation.find(".wz-template").wzAnimationPlay()
                }), sliderConfig.before = function (slider) {
                    $body.hasClass("theme-editing") || $gallery.find(".flex-viewport li:not(.flex-active-slide) [data-animation] .wz-template").stop(!0, !0).wzAnimationPlay("goStep", "0")
                }, sliderConfig.start = function (slider) {
                    if ($gallery.attr("lastSlide") || -1 != slider.currentSlide) {
                        if (sliderConfig.zoom && !$body.hasClass("theme-editing")) {
                            var $img = $gallery.find(".flex-active-slide:not(.clone) img"), img = $img[0];
                            $img.width() < img.naturalWidth && $gallery.find(".flex-active-slide img").ezPlus(ezOptions)
                        }
                        $gallery.trigger("sliderStart"), startFunction && startFunction(), $body.hasClass("theme-review") && $gallery.find(".flex-active-slide [data-animation] .wz-template").wzAnimationPlay("goStep", "0").wzAnimationPlay("play"), $gallery.attr("lastSlide", slider.currentSlide)
                    }
                }, sliderConfig.after = function (slider) {
                    if ($gallery.attr("lastSlide") != slider.currentSlide) {
                        if (sliderConfig.zoom && !$body.hasClass("theme-editing")) {
                            $(".zoomContainer").remove();
                            var $img = $gallery.find(".flex-active-slide:not(.clone) img"), img = $img[0];
                            $img.width() < img.naturalWidth && $gallery.find(".flex-active-slide:not(.clone) img").ezPlus(ezOptions)
                        }
                        afterFunction && afterFunction(), $body.hasClass("theme-editing") ? ($gallery.find(".wz-element-container").hide(), $gallery.find(".flex-active-slide .wz-element-container").show()) : $gallery.find(".flex-active-slide [data-animation] .wz-template").wzAnimationPlay("play"), $gallery.attr("lastSlide", slider.currentSlide)
                    }
                }, sliderConfig.added = function () {
                    sliderConfig.zoom && !$body.hasClass("theme-editing") && (addedTime = setTimeout(function () {
                        clearTimeout(addedTime), $(".zoomContainer").remove();
                        var $img = $gallery.find(".flex-active-slide:not(.clone) img"), img = $img[0];
                        $img.width() < img.naturalWidth && $gallery.find(".flex-active-slide img").ezPlus(ezOptions)
                    }, 50))
                };
                var $images = $gallery.find(".slides li img"), position = sliderConfig.position || "bottom";
                if (delete sliderConfig.position, sliderConfig.sync && $images.length > 1) {
                    var $template = $gallery.parent(".wz-template, .wz-partial-template"),
                        $carousel = $template.find(".wz-gallery-carousel"),
                        $carouselList_1 = $carousel.children(".slides");
                    $images.each(function () {
                        var $image = $(this), $li = $("<li>");
                        $li.appendTo($carouselList_1);
                        var $img = $("<img src=''>").attr("src", $image.attr("src"));
                        $("<div>").addClass("wz-image-ratio").html($img).appendTo($li)
                    });
                    var direction = "top" === position || "bottom" === position ? "horizontal" : "vertical";
                    $template.css({
                        display: "flex",
                        flexDirection: "horizontal" === direction ? "column" : "row"
                    }), "vertical" === direction && $carousel.addClass("wz-gallery-carousel-vertical"), ("top" === position || "left" === position) && $carousel.addClass("wz-gallery-carousel-reverse");
                    var maxItem_1 = Math.floor(($template.width() - 40) / 70);
                    $carousel.flexslider({
                        animation: "slide",
                        controlNav: !1,
                        animationLoop: !1,
                        slideshow: !1,
                        itemWidth: 60,
                        maxItems: maxItem_1,
                        itemMargin: 5,
                        direction: direction,
                        rtl: sliderConfig.rtl,
                        asNavFor: "#" + $gallery.attr("id"),
                        prevText: "",
                        nextText: "",
                        start: function (slider) {
                            if (slider.removeClass("flex-vertical-centered flex-centered"), "vertical" === slider.vars.direction) {
                                var $slides = slider.viewport.find(".slides");
                                $slides.children("li").css({marginBottom: slider.vars.itemMargin || 0}), $slides.height() < slider.viewport.height() && slider.addClass("flex-vertical-centered")
                            } else $(window).trigger("resize"), 1 === slider.pagingCount && slider.addClass("flex-centered")
                        }
                    }), sliderConfig.sync = "#" + $carousel.attr("id")
                }
                var productDevice = (sliderConfig.maxItems, sliderConfig.minItems, $gallery.find(".slides li").length, getDevice());
                if (sliderConfig.hasOwnProperty("desktopItemWidth") && "desktop" !== productDevice) {
                    var galleryWidth = $gallery.width();
                    galleryWidth > 1170 && (galleryWidth = 1170);
                    var items = Math.floor(galleryWidth / sliderConfig.desktopItemWidth);
                    1 > items && (items = 1), "mobile" === productDevice && sliderConfig.hasOwnProperty("mobileColumns") && sliderConfig.mobileColumns && (items = sliderConfig.mobileColumns), "tablet" === productDevice && sliderConfig.hasOwnProperty("tabletColumns") && sliderConfig.tabletColumns && (items = sliderConfig.tabletColumns), sliderConfig.maxItems = items, sliderConfig.minItems = items
                }
                var $slider = $(this), slider = $slider.flexslider(sliderConfig).data("flexslider");
                if (sliderConfig.hasOwnProperty("desktopItemWidth")) {
                    var sliderResize_1 = null;
                    $body.hasClass("wz-production") && "desktop" !== productDevice && $(window).resize(function (event) {
                        $.isWindow(event.target) && (clearTimeout(sliderResize_1), sliderResize_1 = setTimeout(function () {
                            var galleryWidth = $gallery.width();
                            galleryWidth > 1170 && (galleryWidth = 1170);
                            var items = Math.floor(galleryWidth / sliderConfig.desktopItemWidth);
                            1 > items && (items = 1), "mobile" === productDevice && sliderConfig.hasOwnProperty("mobileColumns") && sliderConfig.mobileColumns && (items = sliderConfig.mobileColumns), "tablet" === productDevice && sliderConfig.hasOwnProperty("tabletColumns") && sliderConfig.tabletColumns && (items = sliderConfig.tabletColumns), slider.vars.maxItems = items, slider.vars.minItems = items, setTimeout(function () {
                                slider.resize()
                            }, 10)
                        }, 10))
                    })
                }
            }), $window.resize()
        }, $.fn.cascadeHeight = function (diffHeight, bottom, changeSection) {
            void 0 === changeSection && (changeSection = !0);
            var $element = $(this);
            $element.attr("data-widget");
            if (!$element.is(":visible")) return !1;
            var webziElementSelector = ".wz-element:not(.fixed-element-" + getDevice() + ")";
            if ($element.parents("[wz-autoHeight]").length) return !1;
            if (!$element.length) return !1;
            if (Math.abs(diffHeight) < 1 && $body.hasClass("wz-production")) return !1;
            if ("undefined" == typeof diffHeight) return !1;
            var outsideOfSection = !1, elementOffset = $element.position(), currentHeight = $element.height(),
                elementBottom = elementOffset.top + currentHeight, elementLeft = Math.round(elementOffset.left),
                elementRight = Math.round(elementLeft + $element.width());
            $element.addClass("changed-height-production"), "undefined" != typeof bottom && "keepChanged" !== bottom && (elementBottom = bottom);
            var $section = $element.parents(".wz-section"), $parent = $element.parents(webziElementSelector).first(),
                list = [];
            elementBottom > $section.position().top + $section.height() && (outsideOfSection = !0);
            var leastPositionBottomAtStart = 0, $lastElementStart = $("<div>");
            $section.children(webziElementSelector).each(function () {
                var $this = $(this), bottom = $this.position().top + $this.height();
                bottom > leastPositionBottomAtStart && (leastPositionBottomAtStart = bottom, $lastElementStart = $this)
            });
            var firstSectionGap = Number($section.height()) - Number(leastPositionBottomAtStart);
            if ($parent.length && !$parent.hasClass("parent-changed-height")) {
                var parentOffset = $parent.position();
                $parent.addClass("changed-height parent-changed-height");
                var biggestUnchangedBottom_1 = 0, biggestChangedBottom_1 = elementBottom,
                    $biggestUnchanged_1 = $element, $biggestChanged_1 = $element;
                if ($parent.find(".wz-element-container").first().children(webziElementSelector + ":not(.changed-height)").not($element).each(function () {
                    var $this = $(this), offset = $this.position();
                    if (offset.top + $this.height() >= elementBottom) {
                        var left = Math.round(offset.left), right = Math.round(left + $this.width());
                        if (right >= elementLeft && elementRight >= left) {
                            $this.addClass("changed-height");
                            var bottom_1 = offset.top + $this.height();
                            list.push({
                                element: $this,
                                bottom: bottom_1
                            }), bottom_1 > biggestChangedBottom_1 && (biggestChangedBottom_1 = bottom_1, $biggestUnchanged_1 = $this), $this.css("top", "+=" + diffHeight + "px").addClass("changed-height-production")
                        }
                    }
                }), $parent.find(".wz-element-container").first().children(webziElementSelector + ":not(.changed-height)").not($element).each(function () {
                    var $this = $(this), offset = $this.position(), bottom = offset.top + $this.height();
                    offset.top + $this.height() > biggestUnchangedBottom_1 && (biggestUnchangedBottom_1 = bottom, $biggestChanged_1 = $this)
                }), biggestChangedBottom_1 + diffHeight > biggestUnchangedBottom_1) {
                    var newDiff = diffHeight;
                    biggestUnchangedBottom_1 && biggestUnchangedBottom_1 > biggestChangedBottom_1 && (newDiff = diffHeight - (biggestUnchangedBottom_1 - biggestChangedBottom_1)), list.push({
                        element: $parent,
                        bottom: parentOffset.top + $parent.height(),
                        diffHeight: newDiff
                    }), $parent.css("height", "+=" + newDiff + "px").addClass("changed-height-production")
                } else {
                    var oldDif = $parent.height() - (biggestChangedBottom_1 > biggestUnchangedBottom_1 ? biggestChangedBottom_1 : biggestUnchangedBottom_1),
                        newHeight = biggestUnchangedBottom_1 + oldDif, newDiff = newHeight - $parent.height();
                    list.push({
                        element: $parent,
                        bottom: parentOffset.top + $parent.height(),
                        diffHeight: newDiff
                    }), $parent.css("height", "+=" + newDiff + "px").addClass("changed-height-production")
                }
            }
            var overLapList_1 = [];
            $element.siblings().filter(webziElementSelector + ":not(.changed-height)").each(function () {
                var $this = $(this), offset = $this.position(), bottom = offset.top + $this.height();
                if (bottom >= elementBottom) {
                    var left = Math.round(offset.left), right = Math.round(left + $this.width());
                    right >= elementLeft && elementRight >= left && overLapList_1.push({
                        element: $this,
                        left: left,
                        right: right,
                        bottom: bottom,
                        top: offset.top
                    })
                }
            }), overLapList_1.sort(function (a, b) {
                return a.top > b.top ? 1 : a.top < b.top ? -1 : 0
            }), overLapList_1.forEach(function (item) {
                var $overlapItem = $(item.element), overlapOffset = $overlapItem.position();
                if (overlapOffset.top + $overlapItem.height() >= elementBottom) {
                    var overlapTop_1 = overlapOffset.top,
                        overlapBottom = Math.round(overlapTop_1 + $overlapItem.height()),
                        overlapLeft_1 = Math.round(overlapOffset.left),
                        overlapRight_1 = Math.round(overlapLeft_1 + $overlapItem.width());
                    if (overlapRight_1 >= elementLeft && elementRight >= overlapLeft_1) {
                        var newDiffHeight = diffHeight, leastSiblinsBottom_1 = 0;
                        if ($element.siblings().each(function () {
                            var $sibling = $(this), siblingOffset = $sibling.position(), siblingTop = siblingOffset.top,
                                siblingBottom = siblingTop + $sibling.height(),
                                siblingLeft = Math.round(siblingOffset.left),
                                siblingRight = Math.round(siblingLeft + $sibling.width());
                            siblingRight >= overlapLeft_1 && overlapRight_1 >= siblingLeft && (siblingLeft > elementRight || elementLeft > siblingRight) && overlapTop_1 > siblingBottom && siblingBottom > elementBottom && elementBottom > siblingTop && siblingBottom > leastSiblinsBottom_1 && (leastSiblinsBottom_1 = siblingBottom)
                        }), leastSiblinsBottom_1 > 0 && overlapTop_1 > leastSiblinsBottom_1) if (0 > diffHeight) if (leastSiblinsBottom_1 > elementBottom) leastSiblinsBottom_1 > overlapBottom + diffHeight && (newDiffHeight = !1); else {
                            var oldDiff = overlapOffset.top - elementBottom;
                            leastSiblinsBottom_1 + oldDiff > overlapOffset.top + diffHeight && (newDiffHeight = Number(leastSiblinsBottom_1 + oldDiff) - Number(overlapOffset.top))
                        } else leastSiblinsBottom_1 > elementBottom + diffHeight && (newDiffHeight = !1);
                        newDiffHeight !== !1 && ($overlapItem.addClass("changed-height"), list.push({
                            element: $overlapItem,
                            bottom: overlapOffset.top + $overlapItem.height(),
                            diffHeight: newDiffHeight
                        }), $overlapItem.css("top", "+=" + newDiffHeight + "px").addClass("changed-height-production"))
                    }
                }
            }), list.forEach(function (item) {
                item.element.hasClass("changed-height-size") || (item.element.addClass("changed-height-size"), item.element.cascadeHeight(item.diffHeight, item.bottom))
            }), ("undefined" == typeof bottom || "keepChanged" === bottom) && $element.height("+=" + diffHeight).addClass("changed-height");
            var leastChangedBottom = 0, $leastChangedElement = null;
            $section.children(".wz-element.changed-height").each(function () {
                var $this = $(this), bottom = $this.position().top + $this.height();
                bottom > leastChangedBottom && (leastChangedBottom = bottom, $leastChangedElement = $this)
            });
            var leastUnChangedBottom = 0, $leastUnChangedElement = null;
            if ($section.children(webziElementSelector).each(function () {
                var $this = $(this), bottom = $this.position().top + $this.height();
                bottom > leastUnChangedBottom && (leastUnChangedBottom = bottom, $leastUnChangedElement = $this)
            }), changeSection && ("undefined" == typeof bottom || "keepChanged" === bottom && !$element.hasClass("wz-new-element"))) if ($lastElementStart && $leastChangedElement && $lastElementStart.attr("id") !== $leastChangedElement.attr("id")) {
                if (leastChangedBottom > leastPositionBottomAtStart) {
                    var paddingBottom = $section.height() - leastPositionBottomAtStart;
                    0 > paddingBottom && (paddingBottom = 0), $section.height(leastChangedBottom + paddingBottom + "px")
                }
            } else $section.height("+=" + diffHeight), $section.height(leastUnChangedBottom + firstSectionGap);
            return "undefined" == typeof bottom && $(".changed-height").removeClass("changed-height changed-height-size").removeClass("parent-changed-height"), $section.addClass("changed-height-production"), this
        }, $.fn.validate = function () {
            var $form = $(this);
            return $form.find("[wz-validate]").filter("input, select, textarea, .wz-form-radioButton").removeClass("has-error").each(function () {
                var $input = $(this);
                $input.trigger("blur")
            }), !$form.find("[wz-validate]:visible.has-error, [wz-validate][type=file].has-error").length
        }, $.fn.scrollToElement = function (speed) {
            void 0 === speed && (speed = "slow");
            var sectionTop = $(this).offset().top, freezeHeight = 0;
            $('[wz-section-freeze="on"]').each(function () {
                var $section = $(this), top = $section.position().top;
                sectionTop > top && ($section.hasClass("fixed") || "sticky" === $section.css("position") || (freezeHeight += $section.height()), freezeHeight += $section.height())
            }), "none" === speed ? $("html").scrollTop(sectionTop - freezeHeight) : $("html").animate({scrollTop: sectionTop - freezeHeight}, speed, function () {
            })
        }, $.fn.autoHeight = function () {
            $(this).each(function (index) {
                var $element = $(this), cascadeHeight = function () {
                    var height, widgetName = $element.attr("data-widget"), authoHeight = !0,
                        firstHeight = $element.height();
                    if ($element.css("height", "auto"), height = $element.height(), $element.css("height", firstHeight), $body.hasClass("wz-production") && ("wz-text" === widgetName ? "desktop" === getDevice() ? (!siteLoaded && Math.abs(height - firstHeight) < 25 && (authoHeight = !1), Math.abs(height - firstHeight) < 9 && (authoHeight = !1)) : Math.abs(height - firstHeight) < 50 && (authoHeight = !1) : "wz-shop-cart-icon" === widgetName && (authoHeight = !1)), authoHeight) {
                        var newHeight = height - firstHeight;
                        Math.abs(newHeight) > 0 && $element.cascadeHeight(newHeight)
                    }
                };
                cascadeHeight(), $element.find("img").length > 0 && $element.waitForImages(function () {
                    cascadeHeight()
                })
            })
        }, $.fn.wzParallax = function () {
            $(this).each(function () {
                var $element = $(this), changePosition = function () {
                    var backgroundPositionX = $element.css("background-position").split(" ")[0],
                        scrolled = $window.scrollTop(), velocity = .1, start = $element.offset().top;
                    $element.height() < 200 && (velocity = .2), $element.css("background-attachment", "fixed"), start >= $window.height() ? $element.css("background-position", backgroundPositionX + " " + Math.floor(-(velocity * (scrolled - start))) + "px") : $element.css("background-position", backgroundPositionX + " " + Math.floor(-(velocity * scrolled)) + "px")
                };
                changePosition(), $window.on("scroll.webzi", changePosition)
            })
        }, $.fn.wzSticky = function () {
            var $this = $(this);
            $this.each(function () {
                var $this = $(this);
                $this.attr("data-start-top-position", Math.ceil($this.position().top))
            });
            var stickyStart = function () {
                $this.each(function () {
                    var $sticky = $(this), scrolled = $window.scrollTop(), height = 0;
                    $this.filter(".fixed").not($sticky).each(function () {
                        height += $(this).height()
                    }), 0 === Math.ceil(scrolled) ? ($this.removeClass("fixed"), $this.css("top", 0), $body.css("paddingTop", "0")) : Math.ceil(scrolled + height) > $sticky.attr("data-start-top-position") ? $sticky.hasClass("fixed") || ($body.css("paddingTop", "+=" + $sticky.height()), $sticky.addClass("fixed"), $sticky.css("top", height)) : ($sticky.removeClass("fixed"), $sticky.css("top", 0), $body.css("paddingTop", "-=" + $sticky.height()))
                })
            };
            $window.on("scroll.webzi", stickyStart), stickyStart()
        }, $.fn.showLightBox = function () {
            var $this = $(this), id = $this.children(".wz-section").attr("id");
            if ("link" !== $this.attr("when") && "everyTime" !== $this.attr("repeat")) {
                var last = Number(getCookie(id + "last_show"));
                if (0 !== last) switch ($this.attr("repeat")) {
                    case"once":
                        if (last > 0) return $this;
                    case"everyDay":
                        if (Number(Math.floor(Date.now() / 1e3)) - last < 86400) return $this;
                    case"everyWeek":
                        if (Number(Math.floor(Date.now() / 1e3)) - last < 604800) return $this;
                    default:
                        return $this
                }
                setCookie(id + "last_show", Math.floor(Date.now() / 1e3), 365)
            }
            $this.addClass("show"), $this.show(), $this.find("[data-animation]").wzAnimationPlay("goStep", "0").wzAnimationPlay("play")
        }, $.fn.wzAnimation = function () {
            var $animationList = $(this), webziScrollList = [],
                isMobileReview = $body.hasClass("theme-review") && "desktop" !== getDevice(),
                runAnimation = function ($element) {
                    var top = $element.offset().top;
                    $element.data("startOffset") && (top = $element.data("startOffset").top), top = Number(top);
                    var animated = ($element.height(), $element.attr("data-animated")),
                        animation = $element.attr("data-animation"),
                        duration = $element.attr("data-animation-duration") || .3,
                        delay = $element.attr("data-animation-delay") || 0,
                        scrollTop = $window.scrollTop() + $window.height();
                    if (isMobileReview && (scrollTop = $("#wz-root").height() + $("#wz-root").scrollTop()), scrollTop > top && "true" !== animated) {
                        var id = $element.attr("id");
                        if (animation.startsWith("wz-")) if ("desktop" === getDevice() && "true" === $element.attr("data-animation-on-scroll")) {
                            var speed = $element.attr("data-animation-on-scroll-speed") || 0,
                                screenHeight = Number($window.height()), scrollTop_1 = Number($window.scrollTop()),
                                percent = 1 - (top + -scrollTop_1) / screenHeight;
                            percent = 100 * percent, 130 > percent && percent > -30 && ($element.find(".wz-template").length ? $element.find(".wz-template").wzAnimationPlay("goStep", percent, speed) : $element.wzAnimationPlay("goStep", percent, speed))
                        } else $element.attr("data-animated", "true"), $element.find(".wz-template").length ? $element.find(".wz-template").wzAnimationPlay("play") : $element.wzAnimationPlay("play"); else $element.css("animationDelay", delay + "s"), $element.css("animationDuration", duration + "s"), webziScrollList.hasOwnProperty(id) && clearTimeout(webziScrollList[id]), $element.removeClass("animation-start"), webziScrollList[id] = setTimeout(function () {
                            $element.attr("data-animated", "true"), $element.removeClass(animation), $element.css("animationDelay", ""), $element.css("animationDuration", "")
                        }, 1e3 * (Number(delay) + Number(duration) + .2))
                    }
                };
            $window.on("scroll.webzi", function () {
                $animationList.each(function () {
                    var $item = $(this);
                    runAnimation($item)
                })
            }), $animationList.each(function () {
                var $item = $(this), animation = $item.attr("data-animation");
                if (animation.startsWith("wz-")) {
                    var startOffset = $item.offset();
                    $item.data("startOffset", startOffset), $item.find(".wz-template").length ? "desktop" !== getDevice() && $item.attr("data-" + getDevice() + "-animation") ? $item.find(".wz-template").wzAnimationPlay({prefix: "data-" + getDevice() + "-animation"}) : $item.find(".wz-template").wzAnimationPlay() : $item.wzAnimationPlay()
                }
                $item.addClass("animated animation-start"), $item.addClass($item.attr("data-animation")), runAnimation($item)
            })
        }
    })
}(jQuery);
var WebziCart = function () {
    function WebziCart() {
    }

    return WebziCart.refresh = function (data, openIt) {
        if (void 0 === openIt && (openIt = !0), "success" === data.result) {
            var $miniCart = $(".wz-shop-mini-cart-container"), $miniCartList_1 = $miniCart.find(".cart-product-list");
            $miniCartList_1.html("");
            var template = $("template#shop-cart-item").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                compile_1 = _.template(template);
            data.hasOwnProperty("cart") && (data.cart.hasOwnProperty("items") && $.each(data.cart.items, function (index, item) {
                $miniCartList_1.append(compile_1({item: item}))
            }), data.cart.hasOwnProperty("total") && $miniCart.find(".cart-shop-mini-subtotal .price").text(data.cart.total_exclude_tax.toString().formatNumber().convertToLocalNumber() + currency_sign), data.cart.hasOwnProperty("count") && $(".shop-cart-icon-container .shop-cart-count").text(data.cart.count.toString().formatNumber().convertToLocalNumber())), openIt && $miniCart.addClass("open")
        }
    }, WebziCart.refreshShippingList = function (success) {
        void 0 === success && (success = null), $.getJSON(site_api_url + "shop/cart/getShippingList/", function (result) {
            var template = $("template#checkout-shipping-item-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                compiled = _.template(template), $list = $("#wz-checkout-shipping-step .checkout-shipping-list tbody");
            $list.html(""), result.hasOwnProperty("shipping") && $.each(result.shipping, function (index, shipping) {
                var $item = $(compiled({shipping: shipping}));
                0 == index && ($item.find("input[type=radio]").prop("checked", !0), $item.addClass("selected"), $item.find(".scheduling-list .scheduling-item ").first().addClass("show"), $item.find(".scheduling-time-list .scheduling-time-item").first().addClass("show")), $list.append($item)
            }), $.isFunction(success) && success.call()
        })
    }, WebziCart
}(), WebziValidate = function () {
    function WebziValidate() {
    }

    return WebziValidate.checkMobile = function (mobile) {
        return /^(\+98)?[0]?[9]\d{9}$/.test(mobile)
    }, WebziValidate.checkPhone = function (phone) {
        return /^(\+98)?(0\d{2,3})?\d{7,8}$/.test(phone)
    }, WebziValidate.checkNationalCode = function (code) {
        if (!/^\d{10}$/.test(code) || "0000000000" == code || "1111111111" == code || "2222222222" == code || "3333333333" == code || "4444444444" == code || "5555555555" == code || "6666666666" == code || "7777777777" == code || "8888888888" == code || "9999999999" == code) return !1;
        var i, check = parseInt(code[9]), sum = 0;
        for (i = 0; 9 > i; ++i) sum += parseInt(code[i]) * (10 - i);
        return sum %= 11, 2 > sum && check == sum || sum >= 2 && check + sum == 11
    }, WebziValidate.checkPostCode = function (code) {
        return /^\d{10}$/.test(code)
    }, WebziValidate.checkMail = function (mail) {
        return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(mail)
    }, WebziValidate.isEmpty = function (text) {
        return text.toString().length < 1
    }, WebziValidate
}();
jQuery(document).ready(function ($) {
    "use strict";

    function wcqib_refresh_quantity_increments() {
        jQuery("div.quantity:not(.buttons_added), td.quantity:not(.buttons_added)").each(function (a, b) {
            var c = jQuery(b);
            c.addClass("buttons_added"), c.children().first().before('<input type="button" value="-" class="minus" />'), c.children().last().after('<input type="button" value="+" class="plus" />')
        })
    }

    function login_selected(event) {
        event.preventDefault(), $formLogin.find(".has-error").removeClass("has-error"), $loginFormModel.addClass("is-visible"), $formLogin.addClass("is-selected"), $formRegister.removeClass("is-selected"), $formForgotPasswordCode.removeClass("is-selected"), $formVerifyRegister.removeClass("is-selected"), $formForgotPassword.removeClass("is-selected"), $tabLogin.addClass("selected"), $tabRegister.removeClass("selected");
        var form = $("#wz-login form").get(0);
        form.reset()
    }

    function register_selected(event) {
        event.preventDefault(), $formRegister.find(".has-error").removeClass("has-error"), $loginFormModel.addClass("is-visible"), $formLogin.removeClass("is-selected"), $formForgotPasswordCode.removeClass("is-selected"), $formRegister.addClass("is-selected"), $formForgotPassword.removeClass("is-selected"), $formVerifyRegister.removeClass("is-selected"), $tabLogin.removeClass("selected"), $tabRegister.addClass("selected");
        var form = $("#wz-register form").get(0);
        form.reset()
    }

    function forgot_password_selected(event) {
        event.preventDefault(), $formForgotPassword.find(".has-error").removeClass("has-error"), $formLogin.removeClass("is-selected"), $formRegister.removeClass("is-selected"), $formForgotPasswordCode.removeClass("is-selected"), $formForgotPassword.addClass("is-selected");
        var form = $("#wz-reset-password form").get(0);
        form.reset()
    }

    function forgot_password_code_selected(event, user) {
        event.preventDefault(), $formForgotPasswordCode.find(".has-error").removeClass("has-error"), $formLogin.removeClass("is-selected"), $formRegister.removeClass("is-selected"), $formForgotPassword.removeClass("is-selected"), $formForgotPasswordCode.addClass("is-selected");
        var form = $("#wz-reset-password-code form").get(0);
        form.reset(), $("#wz-reset-password-code [name=user]").val(user)
    }

    var $body = $("body"), $document = $(document), $window = $(window),
        $loader = $(".wz-loader-container.wz-loader-container-fix"), wzShopCompareList = [];
    if ("undefined" != typeof LazyLoad) {
        var lazyLoadInstance = new LazyLoad({
            elements_selector: "[data-lazy]", callback_enter: function (el) {
                var $element = $(el), $parent = $element.parents(".wz-element").first();
                $parent.filter("[wz-autoHeight]").length > 0
            }
        });
        lazyLoadInstance.update()
    }
    if (String.prototype.getDecimals || (String.prototype.getDecimals = function () {
        var a = this, b = ("" + a).match(/(?:\.(\d+))?(?:[eE]([+-]?\d+))?$/);
        return b ? Math.max(0, (b[1] ? b[1].length : 0) - (b[2] ? +b[2] : 0)) : 0
    }), jQuery(document).ready(function () {
        wcqib_refresh_quantity_increments()
    }), jQuery(document).on("updated_wc_div", function () {
        wcqib_refresh_quantity_increments()
    }), jQuery(document).on("click", ".plus, .minus", function () {
        var a = jQuery(this).closest(".quantity").find(".qty"), b = parseFloat(a.val()), c = parseFloat(a.attr("max")),
            d = parseFloat(a.attr("min")), e = a.attr("step"), f = parseFloat(e);
        b && "" !== b && "NaN" !== b || (b = 0), "" !== c && "NaN" !== c || (c = ""), "" !== d && "NaN" !== d || (d = 0), "any" !== e && "" !== e && void 0 !== e && "NaN" !== f || (e = 1), jQuery(this).is(".plus") ? c && b >= c ? a.val(c) : a.val((b + parseFloat(e)).toFixed(e.getDecimals())) : d && d >= b ? a.val(d) : b > 0 && a.val((b - parseFloat(e)).toFixed(e.getDecimals())), a.trigger("change")
    }), calculateFullWidthElement(), $body.hasClass("wz-production")) {
        var lastDevice_1 = getDevice();
        $.changTextFontsSize();
        var str = navigator.userAgent, i = str.indexOf("Instagram");
        if (-1 != i) {
            var $style = $("<style>").appendTo($("head"));
            $style.attr("id", "instagram_style"), $style.html('[data-widget="wz-text"] .wz-element-content, [data-widget="wz-member"] .wz-member-links, .wz-menu > li > a{zoom:0.8;}')
        }
        if ("object" == typeof product_combinations && product_combinations.length > 0) {
            var optionValues_1 = [];
            product_options.length > 0 && product_options.forEach(function (option) {
                if (optionValues_1.length) {
                    var newList_1 = [];
                    option.values.forEach(function (value) {
                        optionValues_1.forEach(function (item) {
                            var netItem = $.extend([], item);
                            netItem.push(value), newList_1.push(netItem)
                        })
                    }), optionValues_1 = newList_1
                } else option.values.forEach(function (value) {
                    optionValues_1.push([value])
                })
            });
            var invisibleCombinations_1 = [];
            optionValues_1.forEach(function (values) {
                var combinationExist = !1;
                product_combinations.forEach(function (combination) {
                    var check = !0;
                    return values.forEach(function (value) {
                        return -1 === combination.optionsId.indexOf(value) ? (check = !1, !1) : void 0
                    }), check ? (combinationExist = !0, !1) : void 0
                }), combinationExist || invisibleCombinations_1.push(values)
            }), invisibleCombinations_1.forEach(function (values) {
                product_combinations.push({visible: "1", in_stock: "0", price: "0", optionsId: values})
            })
        }
        if (showAddressMap(), setTimeout(function () {
            $('.wz-section-lightBox-full[when="load"]').each(function () {
                var $this = $(this);
                setTimeout(function () {
                    $this.showLightBox()
                }, 1e3 * Number($this.attr("delay") || 0))
            }), $window.on("scroll.webzi", function () {
                $('.wz-section-lightBox-full[when="scroll"]:not(.show)').each(function () {
                    var $this = $(this);
                    $window.scrollTop() > $document.height() * parseFloat($this.attr("scroll") || .5) && $this.showLightBox();
                })
            }), $loader.hide().addClass("with-opacity")
        }, 800), setTimeout(function () {
            document.hasOwnProperty("fonts") ? document.fonts.ready.then(function () {
                $("[wz-autoHeight]").autoHeight(), $loader.hide().addClass("with-opacity"), $("div:not(.wz-gallery-slider):not(.wz-section-lightBox-full) [data-animation][data-animation-type=scroll]").wzAnimation()
            }) : setTimeout(function () {
                $("[wz-autoHeight]").autoHeight(), $loader.hide().addClass("with-opacity"), $("div:not(.wz-gallery-slider):not(.wz-section-lightBox-full) [data-animation][data-animation-type=scroll]").wzAnimation()
            }, 150)
        }, 50 * $(".flexslider ").length), $body.hasClass("wz-production") && "undefined" != typeof audiojs) {
            var musics_1 = audiojs.createAll({});
            musics_1[0];
            $(".wz-audio ol li").first().addClass("playing"), $body.on("click", ".wz-audio   ol li", function (e) {
                var audio = musics_1[0];
                e.preventDefault();
                var $this = $(this);
                $this.addClass("playing").siblings().removeClass("playing");
                var src = $this.attr("data-src");
                audio.load(src), audio.play()
            })
        }
        $body.on("change", ".account-new-radio .regular-radio", function () {
            var $this = $(this), $element = $this.parents(".wz-element").first();
            "yes" === $this.val() ? $(".checkout-account-new .wz-cart-address-register").show() : $(".checkout-account-new .wz-cart-address-register").hide(), $element.autoHeight()
        }), $body.on("click", '#wz-root:not(".wz-mobile-shop-filter-show")  .wz-shop-filter-mobile-button', function () {
            "desktop" !== getDevice() && $("#wz-root").addClass("wz-mobile-shop-filter-show")
        }), $body.on("click", "#wz-root.wz-mobile-shop-filter-show .wz-shop-filter-mobile-button", function (event) {
            "desktop" !== getDevice() && ($("#wz-root").removeClass("wz-mobile-shop-filter-show"), $("[wz-autoHeight]").autoHeight())
        }), $body.on("input", "input[wz-data-formula]", function () {
            var $this = $(this), val = $this.val();
            val = val.toString().convertToLatinNumber(), val = "float" === $this.attr("wz-mask") ? val.replace(/[^0-9\.]/g, "") : val.replace(/[^0-9]/g, "");
            var max = $this.attr("wz-max").toString().convertToLatinNumber() || 0;
            parseFloat(val) > parseFloat(max) && (val = max), $this.val(val), $('[data-widget="wz-shop-product"]').calculatePriceFormula()
        }).on("blur", "input[wz-data-formula]", function () {
            var $this = $(this), val = $this.val();
            val = val.toString().convertToLatinNumber(), val = "float" === $this.attr("wz-mask") ? val.replace(/[^0-9\.]/g, "") : val.replace(/[^0-9]/g, "");
            var min = $this.attr("wz-min").toString().convertToLatinNumber() || 0;
            parseFloat(val) < parseFloat(min) && (val = min), $this.val(val), $('[data-widget="wz-shop-product"]').calculatePriceFormula()
        }), $body.on("click", ".wz-site-search-button", function (event) {
            var $button = $(this), $searcch = $button.parents(".wz-site-search"), $input = $searcch.find("input"),
                url = $searcch.attr("wz-search-url");
            $body.hasClass("wz-production") && "gtag" in window && gtag("event", "search", {search_term: $input.val()}), window.location.href = url + "?q=" + $input.val()
        }).on("keypress", ".wz-site-search input", function (e) {
            13 == e.which && $(this).siblings(".wz-site-search-button").click()
        }), $body.on("change", '.wz-shop-per-page select, .wz-shop-sort select, .wz-shop-filter [type="checkbox"]', function () {
            $.wzUpdateShopList()
        }), $body.on("click", ".vjs-play-control, .vjs-big-play-button", function () {
            var $this = $(this);
            !$this.hasClass("vjs-playing") && $body.hasClass("wz-production") && "gtag" in window && gtag("event", "videos", {
                event_category: "play",
                event_label: $this.parents("[data-widget=wz-video]").attr("id")
            })
        }), $(".wz-form-uploadField").change(function () {
            var file = $(this)[0], name = file.files[0].name, text = $(this).next("label").children("span").last();
            text.css({"white-space": "nowrap", overflow: "hidden"}), text.text(name)
        }), $body.on("click", '[data-widget="wz-shop-cart-icon"]', function () {
            var $container = $(".wz-shop-mini-cart-container");
            $container.hasClass("ready") ? $container.addClass("open") : $.ajax({
                method: "GET",
                url: site_api_url + "shop/cart/getItems/",
                dataType: "json",
                success: function (data) {
                    WebziCart.refresh(data), $container.addClass("open ready")
                }
            })
        }), $body.on("click", ".wz-shop-mini-cart-container .cart-remove-item", function (event) {
            event.preventDefault();
            var $this = $(this), $li = $this.parents("li"), id = $this.attr("data-id"),
                name = $li.find(".cart-item-name").text(), model = $li.find(".cart-item-model").text(),
                $input = $li.find("input.qty"), quantity = $input.attr("data-quantity"),
                price = $input.attr("data-price");
            $loader.show(), $.ajax({
                method: "GET",
                url: site_api_url + "shop/cart/remove/",
                dataType: "json",
                data: {id: id},
                success: function (data) {
                    if (WebziCart.refresh(data), $body.hasClass("wz-production") && "gtag" in window) {
                        setTimeout(function () {
                            gtag("event", "remove_from_cart", {
                                items: [{
                                    id: this.id,
                                    name: name,
                                    variant: model,
                                    quantity: quantity,
                                    price: price
                                }]
                            })
                        }, 1e3)
                    }
                    $loader.hide()
                }
            })
        }), $body.on("change", ".shop-cart-container .wz-shop-cart-item-quantity select, .shop-cart-container .wz-shop-cart-item-quantity .input-text.qty, .cart-item-quantity .input-text.qty", function (event) {
            var $select = $(this), quantity = $select.val(), oldQuantity = $select.attr("data-quantity"),
                id = $select.attr("data-id"), price = $select.attr("data-price");
            $loader.show(), $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/changeQuantity/",
                dataType: "json",
                data: {id: id, quantity: quantity},
                success: function (data) {
                    if ("success" !== data.result) $select.val(oldQuantity), data.hasOwnProperty("message") && $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    }); else {
                        WebziCart.refresh(data, !1);
                        var $totalTemplate = $("template#wz-cart-total-price-template");
                        if ($totalTemplate.length) {
                            var template = $totalTemplate.html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                                compiled = _.template(template);
                            $(".shop-cart-container .cart-total-price").html(compiled({cart: data.cart})), $select.parents(".wz-shop-cart-item").find(".wz-shop-cart-item-total").text((Number(price) * quantity).toString().convertToLocalNumber() + currency_sign)
                        }
                        var $shopCartList_1 = $(".wz-shop-cart-list");
                        if ($shopCartList_1.length > 0 && $.each(data.cart.items, function (index) {
                            var $item = $shopCartList_1.find('.qty[data-id="' + this.id + '"]');
                            $item.attr("data-price") != this.price && location.reload()
                        }), $loader.hide(), $body.hasClass("wz-production") && "gtag" in window) {
                            var items_1 = [];
                            $.each(data.cart.items, function (index) {
                                items_1.push({
                                    id: this.id,
                                    name: this.title,
                                    category: this.category,
                                    variant: this.model,
                                    list_position: index,
                                    quantity: this.quantity,
                                    price: this.price
                                })
                            }), setTimeout(function () {
                                gtag("event", "add_to_cart", {items: items_1})
                            }, 1e3)
                        }
                    }
                    $loader.hide()
                }
            })
        }), $body.on("click", ".shop-cart-container .cart-remove-item", function (event) {
            event.preventDefault();
            var $this = $(this), $element = $this.parents(".wz-element").first(), $li = $this.parents("li"),
                id = $this.attr("data-id"), name = $li.find(".cart-item-name").text(),
                model = $li.find(".cart-item-model").text(), $input = $li.find("input.qty"),
                quantity = $input.attr("data-quantity"), price = $input.attr("data-price"),
                $cartList = $this.parents(".shop-cart-container .wz-shop-cart-list");
            $loader.show(), $.ajax({
                method: "GET",
                url: site_api_url + "shop/cart/remove/",
                dataType: "json",
                data: {id: id},
                success: function (data) {
                    if ($cartList.find(".wz-shop-cart-item").length < 2) location.reload(); else {
                        WebziCart.refresh(data, !1);
                        var template = $("template#wz-cart-total-price-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                            compiled = _.template(template);
                        if ($(".shop-cart-container .cart-total-price").html(compiled({cart: data.cart})), $this.parents(".wz-shop-cart-item").remove(), $element.autoHeight(), $loader.hide(), $body.hasClass("wz-production") && "gtag" in window) {
                            setTimeout(function () {
                                gtag("event", "remove_from_cart", {
                                    items: [{
                                        id: this.id,
                                        name: name,
                                        variant: model,
                                        quantity: quantity,
                                        price: price
                                    }]
                                })
                            }, 1e3)
                        }
                    }
                }
            })
        }), $body.on("click", ".wz-shop-mini-cart-container, .wz-shop-mini-cart-container .close-button", function (e) {
            var $target = $(e.target);
            ($target.hasClass("wz-shop-mini-cart-container") || $target.closest(".close-button").length > 0) && $(".wz-shop-mini-cart-container").removeClass("open")
        }), $body.on("click", ".wz-shop-repurchase-order", function (event) {
            event.preventDefault();
            var $this = $(this), orderId = $this.attr("data-order");
            return $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/repurchaseOrder/",
                dataType: "json",
                data: {order_id: orderId},
                success: function (data) {
                    "success" === data.result && (window.location.href = shop_cart_url)
                }
            }), event.preventDefault(), event.stopPropagation(), !1
        }), $body.on("click", ".wz-shop-product-add-cart", function (event) {
            var $this = $(this), productId = $this.attr("data-id"),
                combination = $this.attr("data-combination-id") || null, fields = !1;
            if ($this.siblings("[type=radio]").length > 0 && $this.siblings("[type=radio]").prop("checked", !0), $this.parents(".wz-shop-product-page").length) {
                var combinationObject = $.getSelectedCombination();
                fields = $.getProductFieldValues(), combinationObject && (combination = combinationObject.id)
            }
            var formData = null;
            FormData && (formData = new FormData, formData.append("product_id", productId), combination && formData.append("combination", combination), $.each(fields, function (index, field) {
                formData.append("fields[" + index + "]", field)
            }), $('[data-widget="wz-shop-product"] .wz-shop-product-options [type="file"]').each(function (index) {
                formData.append("files[" + $(this).attr("id").replace("product-field-", "") + "]", this.files[0])
            }));
            var data = {product_id: productId, combination: combination, fields: fields};
            return $loader.show(), $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/add/",
                dataType: "json",
                data: formData ? formData : data,
                processData: !1,
                contentType: !1,
                success: function (data) {
                    if ($loader.hide(), "success" === data.result) {
                        if (WebziCart.refresh(data), $body.hasClass("wz-production") && "gtag" in window) {
                            var items_2 = [];
                            $.each(data.cart.items, function (index) {
                                items_2.push({
                                    id: this.id,
                                    name: this.title,
                                    category: this.category,
                                    variant: this.model,
                                    list_position: index,
                                    quantity: this.quantity,
                                    price: this.price
                                })
                            }), setTimeout(function () {
                                gtag("event", "add_to_cart", {items: items_2})
                            }, 3e3)
                        }
                    } else data.hasOwnProperty("message") && $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    })
                }
            }), event.preventDefault(), event.stopPropagation(), !1
        }), $body.on("click", ".wz-checkout-step-edit", function (event) {
            event.preventDefault();
            var $element = $(this).parents(".wz-element").first();
            $(".wz-checkout-step").removeClass("open");
            var $step = $(this).parents(".wz-checkout-step");
            $step.addClass("open");
            var form = $step.find("#wz-cart-login form").get(0);
            form && form.reset(), setTimeout(showAddressMap, 1e3), $element.autoHeight()
        }).on("click", ".wz-cart-accept-address", function (event) {
            event.preventDefault();
            var data, $form = $("#wz-checkout-address-step .wz-checkout-guest-address-form form"),
                $element = $(this).parents(".wz-element").first();
            if ($form.length) {
                if (!$form.validate()) return !1;
                data = $form.serialize()
            } else {
                var addressId = $(".shop-cart-container [name=address_id]:checked").val();
                data = {address_id: addressId}
            }
            $loader.show(), $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/setCartAddress/",
                dataType: "json",
                data: data,
                success: function (data) {
                    WebziCart.refreshShippingList(function () {
                        data.hasOwnProperty("result") && "success" === data.result ? ($(".wz-checkout-step").removeClass("open"), $("#wz-checkout-shipping-step").addClass("open"), $body.hasClass("wz-production") && "gtag" in window && gtag("event", "set_checkout_option", {
                            checkout_step: 3,
                            checkout_option: "shipping method"
                        }), data.hasOwnProperty("status") && $(".wz-checkout-steps").attr("wz-step", data.status), data.hasOwnProperty("register") && "yes" === data.register && location.reload()) : data.hasOwnProperty("message") && $.toast({
                            text: data.message,
                            showHideTransition: "fade",
                            position: "top-right",
                            hideAfter: 5e3,
                            textAlign: "right",
                            icon: "error"
                        }), $element.autoHeight(), $("#wz-checkout-shipping-step").scrollToElement("none"), $loader.hide()
                    })
                }
            })
        }).on("change", ".shop-cart-container [name=shipping_id]", function () {
            $(".checkout-shipping-item").removeClass("selected "), $(this).parents(".checkout-shipping-item ").addClass("selected"), $(".scheduling-time-list .scheduling-time-item [name='shipping_delivery']").prop("checked", !1)
        }).on("click", ".wz-cart-accept-shipping", function (event) {
            event.preventDefault();
            var $element = $(this).parents(".wz-element").first(),
                shippingId = $(".shop-cart-container [name=shipping_id]:checked").val(),
                shippingDelivery = $(".shop-cart-container [name=shipping_delivery]:checked").val();
            $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/setShipping/",
                dataType: "json",
                data: {shipping_id: shippingId, shipping_delivery: shippingDelivery},
                success: function (data) {
                    if (data.hasOwnProperty("result") && "success" === data.result) {
                        if ($(".wz-checkout-step").removeClass("open"), $("#wz-checkout-payment-step").addClass("open"), data.hasOwnProperty("cart")) {
                            var template = $("template#wz-checkout-summery-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                                compiled = _.template(template);
                            $("#wz-checkout-payment-step .wz-checkout-summery").html(compiled({cart: data.cart}))
                        }
                        data.hasOwnProperty("payments") && data.payments.hasOwnProperty("cod") && ("hidden" === data.payments.cod.visible ? $('.checkout-payment-item[data-id="cod"]').hide() : $('.checkout-payment-item[data-id="cod"]').show()), data.hasOwnProperty("status") && $(".wz-checkout-steps").attr("wz-step", data.status), $body.hasClass("wz-production") && "gtag" in window && gtag("event", "set_checkout_option", {
                            checkout_step: 4,
                            checkout_option: "payment method"
                        }), $element.autoHeight(), $("#wz-checkout-payment-step").scrollToElement("none")
                    } else data.hasOwnProperty("message") && $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    });
                    $loader.hide()
                }
            })
        }).on("click", ".wz-cart-submit-order", function (event) {
            event.preventDefault();
            var paymentMethod = $(".shop-cart-container [name=payment_method]:checked").val(),
                $note = $(".wz-payment-note [name=note]"), $element = $(this).parents(".wz-element").first(), note = "";
            $note.length && (note = $note.val()), $loader.show(), $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/submitOrder/",
                dataType: "json",
                data: {payment_method: paymentMethod, note: note},
                success: function (data) {
                    if (data.hasOwnProperty("remodal")) {
                        var $remodal = $('[data-remodal-id="' + data.remodal + '"]'),
                            remodal = $.remodal.lookup[$remodal.data("remodal")];
                        remodal.open()
                    } else if (data.hasOwnProperty("result") && "success" === data.result) if (data.hasOwnProperty("postData")) {
                        var $form = $("<form />");
                        $form.attr("action", data.url), $form.attr("method", "post");
                        for (var field in data.postData) $form.append('<input type="hidden" name="' + field + '" value="' + data.postData[field] + '" />');
                        $("body").append($form), $form.submit()
                    } else window.location.href = data.url; else data.hasOwnProperty("message") && $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    });
                    $loader.hide(), $element.autoHeight()
                }
            })
        }).on("click", ".wz-cart-accept-account", function (event) {
            event.preventDefault();
            var register = $(".shop-cart-container [name=register]:checked").val(),
                $element = $(this).parents(".wz-element").first(),
                email = $(".wz-cart-address-register #cart-register-email").val(),
                password = $(".wz-cart-address-register #cart-register-password").val(),
                postData = {register: register, email: email, password: password};
            if ($(".wz-cart-address-register #cart-register-key").length > 0 && $(".wz-cart-address-register #cart-register-key").val().toString().length > 0) {
                var pin = $(".wz-cart-address-register #cart-register-key").val(),
                    firstName = $(".wz-cart-address-register #cart-register-first-name").val(),
                    lastName = $(".wz-cart-address-register #cart-register-last-name").val(),
                    $userEmail = $(".wz-cart-address-register #cart-register-user-email"), userEmail = "";
                $userEmail.length > 0 && (userEmail = $userEmail.val()), postData.key = pin, postData.first_name = firstName, postData.last_name = lastName, postData.user_mail = userEmail
            }
            var valide = !0;
            if ("yes" === register) {
                var $form = $(".wz-cart-address-register form");
                $form.validate() || (valide = !1)
            }
            valide && (setTimeout(showAddressMap, 1e3), $loader.show(), $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/setAccount/",
                dataType: "json",
                data: postData,
                success: function (data) {
                    if (data.hasOwnProperty("result") && "success" === data.result) {
                        var isVirtual = $("#wz-checkout-steps").hasClass("is-virtual");
                        if ("yes" === register) if (data.verify) {
                            var $form = $(".wz-cart-address-register");
                            $form.find("[name=email]").hide(), $form.find("input").removeClass("has-error"), $(".verify-complete").show(), $element.autoHeight(), $loader.hide()
                        } else location.reload(); else $(".wz-checkout-step").removeClass("open"), isVirtual ? ($("#wz-checkout-payment-step").addClass("open"), $(".wz-checkout-steps").attr("wz-step", "payment")) : ($("#wz-checkout-address-step").addClass("open"), data.hasOwnProperty("status") && $(".wz-checkout-steps").attr("wz-step", data.status)), $(".shop-cart-container .wz-cart-address-register").hide(), $element.autoHeight(), $loader.hide(), $body.hasClass("wz-production") && "gtag" in window && gtag("event", "set_checkout_option", {
                            checkout_step: 2,
                            checkout_option: "choose address"
                        })
                    } else data.hasOwnProperty("message") && $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    }), $element.autoHeight(), $loader.hide()
                }
            }))
        }).on("click", ".wz-cart-coupon-submit", function (event) {
            event.preventDefault();
            var $form = $(this).parents(".wz-site-form");
            $form.find(".wz-form-coupon-message").removeClass("is-visible");
            var coupon = $("#wz-cart-coupon").val();
            $.ajax({
                method: "POST",
                url: site_api_url + "shop/cart/addCoupon/",
                dataType: "json",
                data: {coupon: coupon},
                success: function (data) {
                    if ("success" === data.result) {
                        var discountInfo_1 = data.discountInfo;
                        Number(discountInfo_1.discountValue);
                        if ($form.find("input").removeClass("has-error"), data.hasOwnProperty("cart")) {
                            WebziCart.refresh(data, !1);
                            var $totalTemplate = $("template#wz-cart-total-price-template");
                            if ($totalTemplate.length) {
                                var template = $totalTemplate.html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                                    compiled = _.template(template);
                                $(".shop-cart-container .cart-total-price").html(compiled({cart: data.cart}))
                            }
                            if ($body.hasClass("wz-production") && "gtag" in window) {
                                setTimeout(function () {
                                    gtag("event", "select_content", {
                                        promotions: [{
                                            id: this.id,
                                            name: discountInfo_1.name
                                        }]
                                    })
                                }, 1e3)
                            }
                        }
                        $form.find(".wz-form-coupon-message").addClass("is-visible").text(discountInfo_1.couponDescription), setTimeout(function () {
                            $form.parents(".wz-element").first().autoHeight()
                        }, 400)
                    } else $form.find("input").addClass("has-error"), $form.find(".wz-form-error-message").text(data.message || "کد تخفیف وارد شده معتبر نمی‌باشد.")
                },
                error: function () {
                    $form.find("input").addClass("has-error"), $form.find(".wz-form-error-message").text("کد تخفیف وارد شده معتبر نمی‌باشد.")
                }
            })
        }).on("click", ".scheduling-list .scheduling-item ", function (event) {
            $(".scheduling-list .scheduling-item").removeClass("show"), $(".scheduling-time-list .scheduling-time-item").removeClass("show"), $('.scheduling-time-list [name="shipping_delivery"]').removeClass("show"), $(".scheduling-time-list .scheduling-time-item [name='shipping_delivery']").prop("checked", !1);
            var $this = $(this);
            $this.addClass("show"), $(".scheduling-time-list .scheduling-time-item[data-id=" + $this.attr("data-id") + "]").addClass("show")
        }), $body.on("change", ".wz-site-form .province", function (event) {
            var $province = $(this), $form = $province.parents("form"), $city = $form.find(".city");
            $city.html("");
            var $section = $form.find("select.section"),
                url = site_api_url + "shop/region/city?province=" + $province.val();
            8 !== Number($province.val()) && $section.hide(), $.wz_cache.hasOwnProperty(url) ? ($.each($.wz_cache[url], function (index, city) {
                $("<option>").val(city.id).text(city.name).appendTo($city)
            }), $form.find(".dropdown.dropdown-list").wzDropDown()) : $.getJSON(url, function (result) {
                $.wz_cache[url] = result.data, $.each(result.data, function (index, city) {
                    $("<option>").val(city.id).text(city.name).appendTo($city)
                })
            })
        }).on("change", ".wz-site-form .city", function (event) {
            var $city = $(this), $form = $city.parents("form"), $section = $form.find("select.section");
            329 === Number($city.val()) ? $section.show() : $section.hide()
        }), $body.on("click", ".wz-add-new-address", function (event) {
            event.preventDefault();
            var $remodal = $('[data-remodal-id="address-edit"]');
            $remodal.find(".fieldset textarea, .fieldset input").removeClass("has-error");
            var form = $remodal.find("form").get(0);
            form.reset();
            var $province = $remodal.find("select.province");
            $province.val($province.attr("data-default-value"));
            var $city = $remodal.find("select.city");
            $city.html("");
            var url = site_api_url + "shop/region/city?province=" + $province.attr("data-default-value");
            if (setTimeout(showAddressMap, 1e3), $.wz_cache.hasOwnProperty(url)) {
                $.each($.wz_cache[url], function (index, city) {
                    $("<option>").val(city.id).text(city.name).appendTo($city)
                }), $city.val($city.attr("data-default-value"));
                var remodal = $.remodal.lookup[$remodal.data("remodal")];
                remodal.open()
            } else $.getJSON(url, function (result) {
                $.wz_cache[url] = result.data, $.each(result.data, function (index, city) {
                    $("<option>").val(city.id).text(city.name).appendTo($city)
                }), $city.val($city.attr("data-default-value"));
                var remodal = $.remodal.lookup[$remodal.data("remodal")];
                remodal.open()
            })
        }).on("click", ".wz-address-edit", function (event) {
            event.preventDefault();
            var $this = $(this), id = $this.attr("data-id"), url = site_api_url + "shop/address/get?id=" + id;
            $.get(url, function (result) {
                if (result.hasOwnProperty("data")) {
                    var address_1 = result.data;
                    setTimeout(function () {
                        showAddressMap(address_1.coordinate)
                    }, 1e3);
                    var $remodal_1 = $('[data-remodal-id="address-edit"]'),
                        $input = $remodal_1.find(".fieldset textarea, .fieldset input");
                    $input.removeClass("has-error");
                    var form = $remodal_1.find("form").get(0);
                    form.reset();
                    var $province = $remodal_1.find("select.province");
                    $province.val(address_1.province);
                    var $city_1 = $remodal_1.find("select.city");
                    $city_1.html("");
                    var $section = $remodal_1.find("select.section"),
                        cityUrl_1 = site_api_url + "shop/region/city?province=" + address_1.province;
                    if ($input.each(function () {
                        var $this = $(this);
                        address_1.hasOwnProperty($this.attr("name")) && $this.val(address_1[$this.attr("name")])
                    }), $.wz_cache.hasOwnProperty(cityUrl_1)) {
                        $.each($.wz_cache[cityUrl_1], function (index, city) {
                            $("<option>").val(city.id).text(city.name).appendTo($city_1)
                        }), $city_1.val(address_1.city);
                        var remodal = $.remodal.lookup[$remodal_1.data("remodal")];
                        remodal.open()
                    } else $.getJSON(cityUrl_1, function (result) {
                        $.wz_cache[cityUrl_1] = result.data, $.each(result.data, function (index, city) {
                            $("<option>").val(city.id).text(city.name).appendTo($city_1)
                        }), $city_1.val(address_1.city);
                        var remodal = $.remodal.lookup[$remodal_1.data("remodal")];
                        remodal.open()
                    });
                    329 === Number(address_1.city) ? ($section.show(), address_1.hasOwnProperty("section") && $section.val(address_1.section)) : $section.hide()
                }
            })
        }).on("click", ".wz-address-delete", function (event) {
            event.preventDefault();
            var $this = $(this), id = $this.attr("data-id"), $element = $(this).parents(".wz-element").first();
            $loader.show();
            var url = site_api_url + "shop/address/delete?id=" + id;
            $.get(url, function (result) {
                if (result.hasOwnProperty("status") && "success" === result.status) {
                    var $parent = $this.parents(".checkout-address");
                    $parent.find("[name=address_id]:checked").length && $parent.parent().children().not($parent).first().find("[name=address_id]").prop("checked", !0), $parent.remove()
                }
                $element.autoHeight(), $loader.hide()
            })
        }).on("click", ".wz-address-confirm", function (event) {
            event.preventDefault();
            var $this = $(this), $remodal = $this.parents(".remodal"),
                remodal = $.remodal.lookup[$remodal.data("remodal")], $element = $('[data-widget="wz-shop-cart"]');
            $loader.show();
            var url = site_api_url + "shop/address/save", $form = $(".wz-checkout-address-form form");
            $form.validate() ? $.post(url, $form.serialize(), function (result) {
                if (result.hasOwnProperty("id") && "success" === result.status) {
                    var address_2 = {},
                        template = $("template#checkout-address-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
                        compiled = _.template(template);
                    $form.serializeArray().forEach(function (data) {
                        "province" === data.name || "city" === data.name ? address_2[data.name] = $form.find("select[name=" + data.name + "] option[value=" + data.value + "]").text() : address_2[data.name] = data.value
                    }), address_2.hasOwnProperty("id") && address_2.id ? $(".checkout-address[data-id=" + address_2.id + "]").replaceWith(compiled({address: address_2})) : (address_2.id = result.id, $(".checkout-address-list").prepend(compiled({address: address_2}))), $(".wz-add-new-address").removeClass("first-address"), remodal.close()
                } else result.hasOwnProperty("messages") && $.each(result.messages, function (name, message) {
                    $(".wz-checkout-address-form form [name=" + name + "]").addClass("has-error").siblings(".wz-form-error-message").text(message)
                });
                $element.autoHeight(), $loader.hide()
            }) : $loader.hide()
        }).on("click", ".wz-address-cancel", function (event) {
            event.preventDefault();
            var $this = $(this), $remodal = $this.parents(".remodal"),
                remodal = $.remodal.lookup[$remodal.data("remodal")];
            remodal.close()
        }), $.renderProductFilter(), $(".dropdown.dropdown-list").wzDropDown(), $(".wz-shop-product-page").renderProductOption(), $(".wz-shop-price-slider").each(function () {
            var $this = $(this), label = $(this).find("label"), min = $(this).attr("data-min-price") || 0,
                max = $(this).attr("data-max-price") || 1e5, $input = $this.find("input");
            $input.ionRangeSlider({
                type: "double",
                min: min,
                max: max,
                from: min,
                to: max,
                step: (max - min) / 20,
                prettify_separator: ",",
                hide_min_max: !0,
                hide_from_to: !0,
                onChange: function (data) {
                    label.text($.wzGetFilterPrice(data.from, data.to))
                },
                onStart: function (data) {
                    label.text($.wzGetFilterPrice(data.from, data.to))
                },
                onFinish: function (data) {
                    $body.hasClass("wz-production") && ($(".wz-shop-price-slider").data("price", data), $.wzUpdateShopList())
                }
            })
        }), $("[wz-background-scroll=parallax]").wzParallax(), $("[wz-section-freeze=on]").not(".wzc-section-footer-full").wzSticky(), $.fn.fancybox && $(".fancybox").each(function () {
            var $this = $(this);
            !$this.attr("data-fancybox") && $this.attr("rel") && $this.attr("data-fancybox", $this.attr("rel"))
        }).fancybox({hash: !1});
        var afterResizeTimeout_1 = null;
        $window.width();
        $window.on("resize", function (event) {
            var afterResizeFunction = function () {
                var $fakeToggle = $("body > .wz-element .wz-menu-toggle");
                if ($fakeToggle.hasClass("open")) {
                    $("#wz-menu-extend").remove();
                    var $mainToggle = $(".wz-element .wz-menu-toggle");
                    $fakeToggle.parents(".wz-element").remove(), $mainToggle.removeClass("animation-off").parents(".wz-element").show()
                }
                lastDevice_1 !== getDevice()
            };
            if (lastDevice_1 !== getDevice()) {
                setTimeout(function () {
                    $("[wz-autoHeight]").autoHeight()
                }, 500), lastDevice_1 = getDevice(), $('.changed-height-production,  [data-widget="wz-text"]').removeAttr("style").removeClass("changed-height-production"), $(".wz-gallery-grid, .wz-gallery-masonry").webziGalleryRender();
                var mvp = document.getElementById("wz-viewport");
                screen.width < 1170 && screen.width > 768 ? mvp.setAttribute("content", "width=767, maximum-scale=1, user-scalable=0") : screen.width < 768 && mvp.setAttribute("content", "width=468, maximum-scale=1, user-scalable=0")
            }
            ($.isWindow(event.target) || lastDevice_1 !== getDevice()) && (clearTimeout(afterResizeTimeout_1), afterResizeTimeout_1 = setTimeout(afterResizeFunction, 500))
        }), $body.on("click", "a", function (event) {
            var $this = $(this), link = $this.attr("href"), $html = $("html, body");
            $body.hasClass("wz-production") && "gtag" in window && ($this.filter("[data-widget=wz-button], [data-widget=wz-image], [data-widget=wz-shape], [data-widget=wz-box]").length > 0 ? $this.filter("[data-widget=wz-button]").length > 0 ? gtag("event", "click", {
                event_category: "button",
                event_label: $this.text()
            }) : gtag("event", "click", {
                event_category: "button",
                event_label: $this.attr("id")
            }) : $this.filter("[href$=checkout]").length > 0 ? gtag("event", "set_checkout_option", {checkout_step: 1}) : $this.parents(".wz-menu").length > 0 && gtag("event", "click", {
                event_category: "menu",
                event_label: $this.text()
            }));
            var sectionTest = /\#.*$/.exec(link);
            if (sectionTest) {
                link = encodeURI(link), link = link.replace(/(\/)?\#.*$/, "").replace(/\/\//g, "/").replace(/^\/|\/$/g, "");
                var currentUrl = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname + window.location.search;
                currentUrl = currentUrl.replace(/\/\//g, "/").replace(/^\/|\/$/g, "");
                var section = sectionTest.toString().replace("#", "") || "none";
                if (link && link === currentUrl && link.toString().length > 3) return $("#" + section).scrollToElement(), event.preventDefault(), !1
            } else "go:top" === link ? ($html.animate({scrollTop: 0}, "slow"), event.preventDefault()) : "go:bottom" === link ? ($html.animate({scrollTop: $document.height()}, "slow"), event.preventDefault()) : "go:close" === link ? ($(this).parents(".wz-section-lightBox-full").hide(), event.preventDefault()) : link && "lightbox" === link.substring(0, 8) && ($(".wzc-section-" + link.replace("lightbox:", "") + "-full").showLightBox(), event.preventDefault())
        }), $(".wz-gallery-grid, .wz-gallery-masonry").webziGalleryRender()
    }
    $body.hasClass("theme-editing") || $body.on("mouseenter", '[data-widget="wz-menu"]', function () {
        var $menu = $(this);
        "desktop" !== getDevice() || $menu.hasClass("wz-menu-clone") || $menu.parents(".wz-section-full").addClass("wz-section-menu-hover")
    }).on("mouseleave", '[data-widget="wz-menu"]', function () {
        $(".wz-section-full").removeClass("wz-section-menu-hover")
    }), $body.on("click", '.wz-section-lightBox-full[background="close"]', function (event) {
        var $targe = $(event.target);
        $targe.filter("[class*=wz-section]").length > 0 && ($targe.hasClass("wz-section-full") ? $targe.hide() : $targe.parent(".wz-section-full").hide())
    }), $body.on("keyup", '[data-widget="wz-table"] .wz-table-search-box', function () {
        var $this = $(this), $container = $this.parents(".wz-table--container"),
            $tbody = $container.find(".wz-table--tbody"), value = $this.val().toLowerCase();
        $tbody.children("tr").filter(function (idx, tr) {
            var $tr = $(tr);
            return $tr.toggle($tr.text().toLowerCase().indexOf(value) > -1)
        })
    }).on("click", ".wz-table--sort-indicator", function () {
        var $this = $(this), $container = $this.parents(".wz-table--container"),
            $tbody = $container.find(".wz-table--tbody"), $th = $this.parent("th"), thIndex = $th.index(),
            reverse = $this.data("reverse") || !1, $sortedCols = $tbody.find("td").filter(function (idx, td) {
                return $(td).index() === thIndex
            }).sort(function (a, b) {
                var isNum = isNaN($.text([a])) === !1 && isNaN($.text([b])) === !1;
                return isNum ? reverse ? $.text([a]) - $.text([b]) : $.text([b]) - $.text([a]) : $.text([a]) > $.text([b]) ? reverse ? -1 : 1 : reverse ? 1 : -1
            });
        $sortedCols.each(function (index, element) {
            return $(element).parent().detach().appendTo($tbody)
        }), $this.data("reverse", !reverse)
    }), $body.on("blur", "[wz-validate]", function (event) {
        var value, $input = $(this), validates = $input.attr("wz-validate").toString().split(","), invalid = null;
        value = $input.hasClass("wz-form-radioButton") ? $input.find("input:checked").val() || "" : $input.val();
        var $inputWidget = $input.parents(".wz-element").filter("[data-widget=wz-inputText], [data-widget=wz-checkbox],[data-widget=wz-dropdown], [data-widget=wz-radioButton], [data-widget=wz-textarea], [data-widget=wz-uploadField]").not('[data-widget="wz-button"]');
        if ($input.removeClass("has-error"), $inputWidget.removeClass("input-invalid").children(".wz-template").removeClass("invalid"), Array.isArray(validates) && validates.forEach(function (validate) {
            if (validate = validate.trim(), "required" === validate) {
                if ("file" === $input.attr("type")) {
                    var input = $input[0];
                    if (0 == input.files.length) return invalid = validate, !1
                } else if ("checkbox" === $input.attr("type")) {
                    if (!$input.is(":checked")) return invalid = validate, !1
                } else if (WebziValidate.isEmpty(value)) return invalid = validate, !1
            } else if (!WebziValidate.isEmpty(value)) switch (validate) {
                case"minLength":
                    if (value.toString().length < Number($input.attr("wz-min-length"))) return invalid = validate, !1;
                    break;
                case"mail":
                case"email":
                    if (!WebziValidate.checkMail(value)) return invalid = validate, !1;
                    break;
                case"mailOrMobile":
                    if ("enamad" === value) return !1;
                    var check = WebziValidate.checkMail(value) || WebziValidate.checkMobile(Number(value.toString().convertToLatinNumber()));
                    if (!check) return invalid = validate, !1;
                    break;
                case"mobile":
                    if (value = value.toString().convertToLatinNumber(), !WebziValidate.checkMobile(value)) return invalid = validate, !1;
                    break;
                case"phone":
                    if (value = value.toString().convertToLatinNumber(), !WebziValidate.checkPhone(value)) return invalid = validate, !1;
                    break;
                case"nationalCode":
                    if (value = value.toString().convertToLatinNumber(), !WebziValidate.checkNationalCode(value)) return invalid = validate, !1;
                    break;
                case"postCode":
                    if (value = value.toString().convertToLatinNumber(), !WebziValidate.checkPostCode(value)) return invalid = validate, !1
            }
        }), invalid) {
            var message = $input.attr("wz-validate-" + invalid) || $input.attr("wz-validate-message");
            $input.addClass("has-error").siblings(".wz-form-error-message, .wz-form-inputText-message").text(message), $inputWidget.addClass("input-invalid").children(".wz-template").addClass("invalid")
        }
    }).on("click", function (event) {
        var $target = $(event.target);
        if ($target.closest("[data-wz-toggle] .dropdown-select").length) {
            var $element = $target.parents('[wz-autoheight], [data-widget="wz-code"]').filter(function () {
                return $(this).parent().is(":not(.wz-shop-recent-filter-container)")
            }).first();
            if ("wz-code" === $element.attr("data-widget") && $element.attr("wz-autoheight", "wz-autoheight"), $element.length) {
                var elementHeight = $element.height(), $dropdown = $target.parents(".dropdown");
                $dropdown.toggleClass("open"), $(".dropdown-list").not($dropdown).removeClass("open"), $target.closest(".dropdown-accordion, .dropdown-tab") && $dropdown.siblings().removeClass("open"), $element.css("height", "auto");
                var height = $element.height();
                $element.css("height", elementHeight), $element.cascadeHeight(height - elementHeight)
            }
        } else $(".dropdown:not(.dropdown-accordion):not(.dropdown-tab)").removeClass("open");
        $target.closest("[data-widget=wz-member]").length || $("[data-widget=wz-member]").removeClass("wz-open-dropdown")
    }).on("click", ".dropdown-list .dropdown-menu > a", function () {
        var $this = $(this), $dropdown = $this.parents(".dropdown-list"), $input = $dropdown.find("select");
        $this.addClass("selected").siblings().removeClass("selected"), $dropdown.children(".dropdown-select").text($this.text()), "hidden" !== $this.attr("hidden") && $dropdown.find(".dropdown-menu [hidden]").length > 0 && $dropdown.find(".dropdown-menu [hidden]").remove(), $input.length && $input.val($this.attr("data-id")).trigger("change")
    }).on("click", ".wz-menu-toggle", function () {
        var $this = $(this);
        if ($body.hasClass("theme-editing")) return !1;
        if ($this.parents("#wz-bar-item-hiddenElements").length) return !0;
        if ($this.hasClass("open") || $this.hasClass("wz-menu-is-open")) {
            var $extend_2 = $("#wz-menu-extend"), $toggle = $("#wz-root").find(".wz-menu-toggle");
            $this.removeClass("open").removeClass("wz-menu-is-open"), $extend_2.removeClass("open"), $(".wz-menu-toggle").removeClass("wz-menu-is-open"), $extend_2.on("transitionend webkitTransitionEnd oTransitionEnd MSTransitionEnd", function () {
                $extend_2.remove(), setTimeout(function () {
                }, 200)
            })
        } else {
            var $element = $this.parents(".wz-element").first(), $menu = $element.find(".wz-menu"),
                isRtl = $menu.hasClass("wz-menu-rtl");
            $menu = $menu.clone().show().removeClass(), $this.addClass("wz-menu-is-open");
            var $toggle = $(".wz-menu-toggle").clone().addClass("open");
            $menu.find(".wz-menu-has-children").each(function () {
                $("<span>").addClass("wz-menu-item-expand").prependTo($(this))
            });
            var $extend_1 = $("<div>").attr("id", "wz-menu-extend").appendTo($body);
            isRtl && $extend_1.addClass("is-rtl"), $extend_1.append($menu), $extend_1.prepend($toggle), setTimeout(function () {
                $extend_1.addClass("open")
            }, 100), setTimeout(function () {
            }, 400)
        }
    }).on("click", "#wz-menu-extend li.wz-menu-has-children > .wz-menu-item-expand, #wz-menu-extend li.wz-menu-has-children > a", function (event) {
        event.preventDefault(), $(this).parents("li.wz-menu-has-children").first().toggleClass("open")
    }).on("click", ".wz-shop-product-compare input", function () {
        var $this = $(this);
        $this.parents(".wz-shop-product-compare").toggleClass("selected");
        var count = $(".wz-shop-product-compare.selected").length;
        if ($this.is(":checked")) wzShopCompareList.push($this.val()); else {
            var index = wzShopCompareList.indexOf($this.val());
            index > -1 && wzShopCompareList.splice(index, 1)
        }
        var $compareList = $("#wz-shop-compare-list");
        count > 0 ? ($compareList.find(".number").html(count.toString().convertToLocalNumber()), $compareList.attr("href", site_url + "shop/compare/" + wzShopCompareList.join("/")), $compareList.show()) : $compareList.hide()
    }).on("click", "[data-widget=wz-gallery] .more-image", function (event) {
        if (event.preventDefault(), $body.hasClass("theme-editing")) return !1;
        var $this = $(this), $element = $this.parents(".wz-element").first(),
            columns = $element.attr("data-columns") || 1, rows = $element.attr("data-rows") || 1,
            imageCount = rows * columns;
        $body.hasClass("wz-production") && (columns = $element.attrDevice("data-columns") || 1, rows = $element.attrDevice("data-rows") || 1, imageCount = $element.attrDevice("data-grid-size") || 9);
        var $hiddenElements = $element.find(".hide");
        $hiddenElements.length <= imageCount && ($this.hide(), $this.removeClass("show")), $hiddenElements = $hiddenElements.slice(0, imageCount), $hiddenElements.removeClass("hide");
        var height = $hiddenElements.outerHeight();
        height *= Math.min(rows, Math.ceil($hiddenElements.length / columns)), $element.cascadeHeight(height)
    }).on("click", "[data-widget=wz-contact] button, .wz-blog-comment-from form button", function (event) {
        var $element = $(this).parents(".wz-element").first(), elementId = $element.attr("id"),
            $contactForm = $element.find("form"), $formResult = $contactForm.find(".form-result"),
            $mailInput = $contactForm.find("[type=email]").val(), valid = !0;
        if (event.preventDefault(), $formResult.removeClass("form-result-show").removeClass("form-result-error"), $contactForm.find("input.input-required, textara.input-required").each(function () {
            var $input = $(this);
            return WebziValidate.isEmpty($input.val()) ? ($formResult.addClass("form-result-show form-result-error"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "requiredInputMessage")), valid = !1, !1) : void 0
        }), !valid || WebziValidate.isEmpty($mailInput) || WebziValidate.checkMail($mailInput) || ($formResult.addClass("form-result-show form-result-error"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "invalidMailMessage")), valid = !1), valid) {
            var contactUrl = void 0;
            contactUrl = $contactForm.attr("action") ? $contactForm.attr("action") : WidgetSetting.getWidgetSetting(elementId, "submitUrl"), contactUrl ? $.ajax({
                method: "POST",
                url: contactUrl,
                dataType: "json",
                data: $contactForm.serialize(),
                success: function (data) {
                    data.hasOwnProperty("result") && "success" === data.result ? ($formResult.addClass("form-result-show"), data.message ? $formResult.text(data.message) : ($formResult.text(WidgetSetting.getWidgetSetting(elementId, "successMessage")), $body.hasClass("wz-production") && "gtag" in window && gtag("event", "submit", {
                        event_category: "contact_us",
                        event_label: elementId
                    })), $contactForm.find("input, textarea").val("")) : ($formResult.addClass("form-result-show form-result-error"), data.hasOwnProperty("messages") ? $formResult.text(data.messages[Object.keys(data.messages)[0]]) : $formResult.text(WidgetSetting.getWidgetSetting(elementId, "failedMessage")))
                },
                error: function () {
                    $formResult.addClass("form-result-show form-result-error"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "failedMessage"))
                }
            }) : ($formResult.addClass("form-result-show"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "successMessage")))
        }
    }).on("click", "[data-widget=wz-form]  [data-widget=wz-button]", function (event) {
        if (event.preventDefault(), !$body.hasClass("wz-production")) return !0;
        var $element = $(this).parents("[data-widget=wz-form]"), elementId = $element.attr("id"),
            $form = $element.find("form"), form = $form[0], $formResult = $element.find(".form-result"),
            valid = $form.validate(), formdata = !1;
        if (FormData && (formdata = new FormData(form)), $formResult.removeClass("form-result-show").removeClass("form-result-error"), !valid) return !1;
        var Url = ($form.serialize(), void 0);
        Url = $form.attr("action") ? $form.attr("action") : WidgetSetting.getWidgetSetting(elementId, "submitUrl"), Url ? ($loader.show(), $.ajax({
            method: "POST",
            url: Url,
            dataType: "json",
            data: formdata ? formdata : $form.serialize(),
            processData: !1,
            contentType: !1,
            success: function (data) {
                if ($formResult.length > 0 && $formResult.addClass("form-result-show"), data.hasOwnProperty("result") && "success" === data.result) {
                    var form_1 = $form.get(0);
                    form_1.reset();
                    var a = /utm_ma=(.*)/.exec(window.location.search);
                    if (a) {
                        var b = "https://s1.mediaad.org/serve/post-back?clickId=".concat(a[1], "&actionName=registert"),
                            c = new XMLHttpRequest;
                        c.open("GET", b, !0), c.send()
                    }
                    data.hasOwnProperty("redirect") ? location.replace(data.redirect) : data.message && ($formResult.length > 0 ? $formResult.removeClass("form-result-error").text(data.message) : $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "success"
                    }), $body.hasClass("wz-production") && "gtag" in window && gtag("event", "submit", {
                        event_category: "form",
                        event_label: data.formName || elementId
                    }))
                } else if (data.message || data.hasOwnProperty("messages")) {
                    var massage = data.messages[Object.keys(data.messages)[0]] || data.message;
                    $formResult.length > 0 ? $formResult.addClass("form-result-error").text(massage) : $.toast({
                        text: massage,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "error"
                    })
                }
                $loader.hide()
            },
            error: function () {
                $formResult.addClass("form-result-show form-result-error"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "failedMessage")), $loader.hide()
            }
        })) : ($formResult.addClass("form-result-show"), $formResult.text(WidgetSetting.getWidgetSetting(elementId, "successMessage")))
    }).on("click", ".wz-blog-comment-reply  a", function (event) {
        event.preventDefault(), event.stopPropagation();
        var $blogForm = $(".wz-blog-comment-from"), $element = $blogForm.parents('[data-widget="wz-blog-comment"]'),
            $oldReplyMessage = $blogForm.find(".wz-blog-comment-reply-comment");
        $oldReplyMessage.length && $oldReplyMessage.remove();
        var $replyMessage = $("<div>").addClass("wz-blog-comment-reply-comment").prependTo($blogForm.find(".wz-form-section").first()),
            $comment = $(this);
        $("<div>").text("ارسال پاسخ برای نظر " + $comment.attr("data-name") + ". ").append($('<a class="wz-blog-comment-reply-cancel" hre="">انصراف</a>')).appendTo($replyMessage), $("<input>").attr("type", "hidden").attr("name", "parent").val($comment.attr("data-id")).appendTo($replyMessage), $oldReplyMessage.length || $element.cascadeHeight($replyMessage.outerHeight()), $("html, body").animate({scrollTop: $blogForm.offset().top}, 250)
    }).on("click", ".wz-blog-comment-reply-cancel", function (event) {
        event.preventDefault(), event.stopPropagation();
        var $blogForm = $(".wz-blog-comment-from"), $oldReplyMessage = $blogForm.find(".wz-blog-comment-reply-comment");
        if ($oldReplyMessage.length) {
            var height = $oldReplyMessage.outerHeight(),
                $element = $blogForm.parents('[data-widget="wz-blog-comment"]');
            $oldReplyMessage.remove(), $element.cascadeHeight(-height)
        }
    }).on("click", ".submit-input-change input[type=radio]", function (event) {
        var $input = $(this), $form = $input.parents("form");
        $.ajax({
            method: "POST",
            url: $form.attr("action"),
            dataType: "json",
            data: $form.serialize(),
            success: function (data) {
                data.message && $.toast({
                    text: data.message,
                    showHideTransition: "fade",
                    position: "top-right",
                    hideAfter: 5e3,
                    textAlign: "right",
                    icon: data.result || "success"
                })
            }
        })
    }).on("click", ".wz-tabs-container .wz-tabs li", function () {
        var $this = $(this), id = $this.attr("for"), $container = $this.parents(".wz-tabs-container"),
            $element = $this.parents(".wz-element").first();
        $this.addClass("open").siblings().removeClass("open"), $container.find(".wz-tab-content").removeClass("open"), $container.find("#" + id).addClass("open");
        var elementHeight = $element.height();
        $element.css("height", "auto");
        var height = $element.height();
        $element.css("height", elementHeight), $element.cascadeHeight(height - elementHeight)
    }).on("mouseover", ".wz-shop-sort-option-checkbox .regular-checkbox-color", function () {
        var $this = $(this);
        $this.parents(".wz-shop-filter-item").find(".wz-shop-filter-color-result").text(" : " + $this.attr("data-title"))
    }).on("mouseleave", ".wz-shop-sort-option-checkbox .regular-checkbox-color", function () {
        var $this = $(this);
        $this.parents(".wz-shop-filter-item").find(".wz-shop-filter-color-result").text("")
    }).on("click", ".wz-shop-product-colors .regular-checkbox-color", function () {
        var $this = $(this);
        $this.parents(".wz-shop-product-colors").find(".wz-shop-product-color-result").text(" : " + $this.attr("data-title"))
    }).on("mouseover", ".wz-element[data-animation][data-animation-type=hover]", function () {
        if ($body.hasClass("theme-editing")) return !1;
        var $this = $(this), animation = $this.attr("data-animation"),
            duration = $this.attr("data-animation-duration") || .5, delay = $this.attr("data-animation-delay") || 0;
        $this.attr("data-animated") || ($this.css("animationDelay", delay + "s"), $this.css("animationDuration", duration + "s"), $this.addClass("animated"), $this.attr("data-animated", "true"), $this.addClass(animation).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function () {
            $(this).removeClass(animation), $this.css("animationDelay", ""), $this.css("animationDuration", "")
        }))
    }).on("mouseleave", ".wz-element[data-animation][data-animation-type=hover]", function () {
        $(this).removeAttr("data-animated")
    }), $window.on("resize", function () {
        calculateFullWidthElement()
    }), $body.on("click", "#wz-member-logout", function (event) {
        event.preventDefault(), $loader.show();
        var $widget = $('[data-widget="wz-member"]');
        $widget.removeClass("wz-open-dropdown"), setTimeout(function () {
            $widget.removeClass("wz-element-top-index")
        }, 400), $.ajax({
            method: "GET",
            url: site_api_url + "customer/member/logout/",
            dataType: "json",
            success: function (data) {
                location.reload()
            }
        })
    }).on("click", "#wz-member-orders", function (event) {
        event.preventDefault();
        var $widget = $('[data-widget="wz-member"]');
        $widget.removeClass("wz-open-dropdown"), setTimeout(function () {
            $widget.removeClass("wz-element-top-index")
        }, 400), $loader.show();
        var $remodal = $('[data-remodal-id="wz-member-order-list"]'),
            template = $("template#wz-member-order-list-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
            compiled = _.template(template);
        $.ajax({
            method: "GET", url: site_api_url + "shop/cart/getOrders/", dataType: "json", success: function (data) {
                var orders = data.hasOwnProperty("orders") ? data.orders : [];
                $("#wz-member-order-items").html(compiled({orders: orders})), setTimeout(function () {
                    $loader.hide();
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.open()
                }, 100)
            }
        })
    }).on("click", ".wz-member-order-item-details", function (event) {
        event.preventDefault();
        var id = $(this).attr("data-id");
        $loader.show();
        var $remodal = $('[data-remodal-id="wz-member-order-item"]'),
            template = $("template#wz-member-order-item-details-template").html().replace(RegExp("&lt;%", "g"), "<%").replace(RegExp("%&gt;", "g"), "%>"),
            compiled = _.template(template);
        $.ajax({
            method: "GET",
            url: site_api_url + "shop/cart/getOrder?id=" + id,
            dataType: "json",
            success: function (data) {
                var order = data.hasOwnProperty("order") ? data.order : {},
                    products = data.hasOwnProperty("products") ? data.products : [];
                $("#wz-member-order-item-details").html(compiled({
                    order: order,
                    products: products
                })), setTimeout(function () {
                    $loader.hide();
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.open()
                }, 100)
            }
        })
    }).on("click", "#wz-member-password", function (event) {
        event.preventDefault();
        var $widget = $('[data-widget="wz-member"]');
        $widget.removeClass("wz-open-dropdown"), setTimeout(function () {
            $widget.removeClass("wz-element-top-index")
        }, 400);
        var $remodal = $('[data-remodal-id="wz-member-password-edit"]');
        $remodal.find(".fieldset textarea, .fieldset input").removeClass("has-error");
        var form = $remodal.find("form").get(0);
        form.reset();
        var remodal = $.remodal.lookup[$remodal.data("remodal")];
        remodal.open()
    }).on("click", "#wz-member-profile", function (event) {
        event.preventDefault();
        var $widget = $('[data-widget="wz-member"]');
        $widget.removeClass("wz-open-dropdown"), setTimeout(function () {
            $widget.removeClass("wz-element-top-index")
        }, 400);
        var $remodal = $('[data-remodal-id="wz-member-profile-edit"]');
        $remodal.find(".fieldset textarea, .fieldset input").removeClass("has-error");
        var $form = $remodal.find("form"), form = $form.get(0);
        form.reset(), $.ajax({
            method: "GET",
            url: site_api_url + "customer/member/profile/",
            dataType: "json",
            success: function (result) {
                var remodal = $.remodal.lookup[$remodal.data("remodal")];
                remodal.open(), result.hasOwnProperty("data") && $.each(result.data, function (field, value) {
                    $form.find("[name=" + field + "]").val(value)
                })
            }
        })
    }).on("click", "#wz-member-profile-save", function (event) {
        event.preventDefault();
        var $remodal = $('[data-remodal-id="wz-member-profile-edit"]'), $form = $remodal.find("form");
        $form.validate() && ($loader.show(), $.ajax({
            method: "POST",
            data: $form.serialize(),
            url: site_api_url + "customer/member/profile/",
            dataType: "json",
            success: function (data) {
                if ($loader.hide(), data.hasOwnProperty("messages") && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), data.hasOwnProperty("result") && "success" === data.result) {
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.close()
                }
            },
            error: function () {
                $loader.hide()
            }
        }))
    }).on("click", "#wz-company-profile-edit", function (event) {
        event.preventDefault();
        var $widget = $('[data-widget="wz-member"]');
        $widget.removeClass("wz-open-dropdown"), setTimeout(function () {
            $widget.removeClass("wz-element-top-index")
        }, 400);
        var $remodal = $('[data-remodal-id="wz-company-profile-edit"]');
        $remodal.find(".fieldset textarea, .fieldset input").removeClass("has-error");
        var $form = $remodal.find("form"), form = $form.get(0);
        form.reset(), $.ajax({
            method: "GET",
            url: site_api_url + "customer/member/company_profile/",
            dataType: "json",
            success: function (result) {
                var remodal = $.remodal.lookup[$remodal.data("remodal")];
                remodal.open(), result.hasOwnProperty("data") && $.each(result.data, function (field, value) {
                    $form.find("[name=" + field + "]").val(value)
                })
            }
        })
    }).on("click", "#wz-company-profile-save", function (event) {
        event.preventDefault();
        var $remodal = $('[data-remodal-id="wz-company-profile-edit"]'), $form = $remodal.find("form");
        $form.validate() && ($loader.show(), $.ajax({
            method: "POST",
            data: $form.serialize(),
            url: site_api_url + "customer/member/company-profile/",
            dataType: "json",
            success: function (data) {
                if ($loader.hide(), data.hasOwnProperty("messages") && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), data.hasOwnProperty("result") && "success" === data.result) {
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.close()
                }
            },
            error: function () {
                $loader.hide()
            }
        }))
    }).on("click", "#wz-shop-add-comment", function (event) {
        event.preventDefault();
        var $remodal = $('[data-remodal-id="wz-shop-comment-from-open"]'), $form = $remodal.find("form");
        $form.find(".has-error").removeClass("has-error"), $form.validate() && ($loader.show(), $.ajax({
            method: "POST",
            data: $form.serialize(),
            url: $form.attr("action"),
            dataType: "json",
            success: function (data) {
                if ($loader.hide(), data.hasOwnProperty("messages") && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), data.hasOwnProperty("result") && "success" === data.result) {
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.close(), $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 5e3,
                        textAlign: "right",
                        icon: "success"
                    })
                }
            },
            error: function () {
                $loader.hide()
            }
        }))
    }).on("click", "#wz-member-password-reset", function (event) {
        event.preventDefault();
        var $remodal = $('[data-remodal-id="wz-member-password-reset"]'), $form = $remodal.find("form"),
            queries = $.getUrlQueries();
        if ($form.validate() && queries.hasOwnProperty("key")) {
            $loader.show();
            var url = site_api_url + "customer/member/change_password?key=" + queries.key;
            queries.hasOwnProperty("user") && (url += "&user=" + queries.user), $.ajax({
                method: "POST",
                data: $form.serialize(),
                url: url,
                dataType: "json",
                success: function (data) {
                    data.hasOwnProperty("messages") && ($loader.hide(), $.each(data.messages, function (field, message) {
                        $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                    })), data.hasOwnProperty("result") && "success" === data.result && (window.location.href = site_url)
                },
                error: function () {
                    $loader.hide()
                }
            })
        }
    }).on("click", "#wz-member-password-save", function (event) {
        event.preventDefault();
        var $remodal = $('[data-remodal-id="wz-member-password-edit"]'), $form = $remodal.find("form");
        $form.validate() && ($loader.show(), $.ajax({
            method: "POST",
            data: $form.serialize(),
            url: site_api_url + "customer/member/change_password/",
            dataType: "json",
            success: function (data) {
                if ($loader.hide(), data.hasOwnProperty("messages") && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), data.hasOwnProperty("result") && "success" === data.result) {
                    var remodal = $.remodal.lookup[$remodal.data("remodal")];
                    remodal.close()
                }
            },
            error: function () {
                $loader.hide()
            }
        }))
    }).on("click", "#wz-shop-comment-from-open, .wz-shop-comment-reply", function (event) {
        event.preventDefault();
        var reply = $(this).attr("data-reply"), $remodal = $('[data-remodal-id="wz-shop-comment-from-open"]');
        $remodal.find(".fieldset textarea, .fieldset input").removeClass("has-error"), $remodal.find("[name=parent]").val(reply);
        var form = $remodal.find("form").get(0);
        form.reset();
        var remodal = $.remodal.lookup[$remodal.data("remodal")];
        remodal.open()
    }), $body.on("click", '[data-widget="wz-member"]', function (event) {
        var $this = $(this);
        return $this.find(".wz-member-welcome").length && ($this.addClass("wz-element-top-index wz-open-dropdown"), $this.parents(".wz-element").addClass("wz-element-top-index")), event.preventDefault(), !1
    });
    var $loginFormModel = $(".wz-user-modal, .wz-user-inline-form"),
        $formLogin = $loginFormModel.find("#wz-login").add($("#wz-cart-login")),
        $formRegister = $loginFormModel.find("#wz-register"),
        $formVerifyRegister = $loginFormModel.find("#wz-verify-register"),
        $formForgotPassword = $loginFormModel.find("#wz-reset-password"),
        $formForgotPasswordCode = $loginFormModel.find("#wz-reset-password-code"),
        $formModalTab = $(".wz-user-modal-switcher"), $tabLogin = $formModalTab.children("li").eq(0).children("a"),
        $tabRegister = $formModalTab.children("li").eq(1).children("a"),
        $forgotPasswordLink = $formLogin.find(".wz-user-modal-bottom-message a"),
        $backToLoginLink = $formForgotPassword.find(".wz-user-modal-bottom-message a");
    $body.on("click", ".wz-login", login_selected), $body.on("click", ".wz-register", register_selected), $loginFormModel.on("click", function (event) {
        $(event.target).is($loginFormModel) && $loginFormModel.removeClass("is-visible")
    }), $document.keyup(function (event) {
        27 === event.which && $loginFormModel.removeClass("is-visible")
    }), $formModalTab.on("click", function (event) {
        $(event.target).is($tabLogin) ? login_selected(event) : register_selected(event)
    }), $forgotPasswordLink.on("click", function (event) {
        forgot_password_selected(event)
    }), $backToLoginLink.on("click", function (event) {
        login_selected(event)
    }), $formLogin.find('input[type="submit"], .wz-login-submit').on("click", function (event) {
        var $form = $(this).parents("form"), isValid = $form.validate(), ajax = $form.attr("data-ajax") || "true";
        isValid ? "true" === ajax ? (event.preventDefault(), $loader.show(), $.ajax({
            method: "POST",
            url: site_api_url + "customer/member/login/",
            dataType: "json",
            data: $form.serialize(),
            success: function (data) {
                data.hasOwnProperty("result") && "success" === data.result ? ($body.hasClass("wz-production") && "gtag" in window && gtag("event", "login", {
                    method: "Site",
                    user: $form.find("#login-email").val()
                }), location.reload()) : data.hasOwnProperty("messages") && "object" == typeof data.messages && ($.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), $loader.hide())
            }
        })) : $form.submit() : event.preventDefault()
    }), $("#wz-register, #wz-verify-register").find('input[type="submit"]').on("click", function (event) {
        var $form = $(this).parents("form"), isValid = $form.validate();
        event.preventDefault(), isValid && ($loader.show(), $.ajax({
            method: "POST",
            url: site_api_url + "customer/member/register/",
            dataType: "json",
            data: $form.serialize(),
            success: function (data) {
                if (data.hasOwnProperty("result") && "success" === data.result) if ($body.hasClass("wz-production") && "gtag" in window && gtag("event", "sign_up", {
                    method: "Site",
                    user: $form.find("#register-email").val()
                }), data.verify) $formRegister.removeClass("is-selected"), $formVerifyRegister.addClass("is-selected"), $formVerifyRegister.find("[name=email]").val($form.find("[name=email]").val()); else if (data.hasOwnProperty("message")) {
                    $loader.hide();
                    var $loginFormModel_1 = $(".wz-user-modal, .wz-user-inline-form");
                    $loginFormModel_1.removeClass("is-visible"), $.toast({
                        text: data.message,
                        showHideTransition: "fade",
                        position: "top-right",
                        hideAfter: 9e3,
                        textAlign: "right",
                        icon: "success"
                    })
                } else data.hasOwnProperty("redirect") ? location.replace(data.redirect) : location.reload(); else data.hasOwnProperty("messages") && "object" == typeof data.messages && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                });
                $loader.hide()
            }
        }))
    }), $formForgotPassword.find('input[type="submit"]').on("click", function (event) {
        event.preventDefault();
        var $form = $(this).parents("form"), isValid = $form.validate();
        isValid && ($loader.show(), $.ajax({
            method: "POST",
            url: site_api_url + "customer/member/reset_password/",
            dataType: "json",
            data: $form.serialize(),
            success: function (data) {
                data.hasOwnProperty("result") && "success" === data.result ? data.hasOwnProperty("mobile") && "true" === data.mobile ? forgot_password_code_selected(event, $form.find("[name=email]").val()) : ($loginFormModel.removeClass("is-visible"), $.toast({
                    text: data.message,
                    showHideTransition: "fade",
                    position: "top-right",
                    hideAfter: 9e3,
                    textAlign: "right",
                    icon: "success"
                })) : data.hasOwnProperty("messages") && "object" == typeof data.messages && $.each(data.messages, function (field, message) {
                    $form.find("[name='" + field + "']").addClass("has-error").siblings("span.wz-form-error-message").text(message)
                }), $loader.hide()
            }
        }))
    }), $formForgotPasswordCode.find('input[type="submit"]').on("click", function (event) {
        event.preventDefault();
        var $form = $(this).parents("form"), isValid = $form.validate();
        isValid && (window.location.href = site_url + "?" + $form.serialize() + "#wz-member-password-reset")
    })
}), $(window).on("load", function () {
    var $section = null;
    window.location.hash && -1 !== window.location.hash.toString().indexOf("wz-section") && ($section = $(window.location.hash)), setTimeout(function () {
        document.hasOwnProperty("fonts") ? document.fonts.ready.then(function () {
            siteLoaded = !0, $("[wz-autoHeight]").autoHeight(), $section && $(window).scrollTop() && $section.scrollToElement("none")
        }) : setTimeout(function () {
            siteLoaded = !0, $("[wz-autoHeight]").autoHeight(), $section && $section.scrollToElement("none")
        }, 50)
    }, 90 * $(".flexslider ").length)
});