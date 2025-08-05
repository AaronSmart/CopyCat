
(function(l, r) { if (!l || l.getElementById('livereloadscript')) return; r = l.createElement('script'); r.async = 1; r.src = '//' + (self.location.host || 'localhost').split(':')[0] + ':35729/livereload.js?snipver=1'; r.id = 'livereloadscript'; l.getElementsByTagName('head')[0].appendChild(r) })(self.document);
(function (global, factory) {
  typeof exports === 'object' && typeof module !== 'undefined' ? module.exports = factory(require('react'), require('@depay/web3-blockchains'), require('@depay/widgets'), require('@depay/react-shadow-dom'), require('react-dom')) :
  typeof define === 'function' && define.amd ? define(['react', '@depay/web3-blockchains', '@depay/widgets', '@depay/react-shadow-dom', 'react-dom'], factory) :
  (global = typeof globalThis !== 'undefined' ? globalThis : global || self, global.DePayButtons = factory(global.React, global.Web3Blockchains, global.DePayWidgets, global.ReactShadowDOM, global.ReactDOM));
})(this, (function (React, Blockchains, DePayWidgets, reactShadowDom, m) { 'use strict';

  function _interopDefaultLegacy (e) { return e && typeof e === 'object' && 'default' in e ? e : { 'default': e }; }

  var React__default = /*#__PURE__*/_interopDefaultLegacy(React);
  var Blockchains__default = /*#__PURE__*/_interopDefaultLegacy(Blockchains);
  var DePayWidgets__default = /*#__PURE__*/_interopDefaultLegacy(DePayWidgets);
  var m__default = /*#__PURE__*/_interopDefaultLegacy(m);

  function _typeof(o) {
    "@babel/helpers - typeof";

    return _typeof = "function" == typeof Symbol && "symbol" == typeof Symbol.iterator ? function (o) {
      return typeof o;
    } : function (o) {
      return o && "function" == typeof Symbol && o.constructor === Symbol && o !== Symbol.prototype ? "symbol" : typeof o;
    }, _typeof(o);
  }

  function toPrimitive(t, r) {
    if ("object" != _typeof(t) || !t) return t;
    var e = t[Symbol.toPrimitive];
    if (void 0 !== e) {
      var i = e.call(t, r || "default");
      if ("object" != _typeof(i)) return i;
      throw new TypeError("@@toPrimitive must return a primitive value.");
    }
    return ("string" === r ? String : Number)(t);
  }

  function toPropertyKey(t) {
    var i = toPrimitive(t, "string");
    return "symbol" == _typeof(i) ? i : i + "";
  }

  function _defineProperty(e, r, t) {
    return (r = toPropertyKey(r)) in e ? Object.defineProperty(e, r, {
      value: t,
      enumerable: !0,
      configurable: !0,
      writable: !0
    }) : e[r] = t, e;
  }

  function _arrayLikeToArray(r, a) {
    (null == a || a > r.length) && (a = r.length);
    for (var e = 0, n = Array(a); e < a; e++) n[e] = r[e];
    return n;
  }

  function _arrayWithoutHoles(r) {
    if (Array.isArray(r)) return _arrayLikeToArray(r);
  }

  function _iterableToArray(r) {
    if ("undefined" != typeof Symbol && null != r[Symbol.iterator] || null != r["@@iterator"]) return Array.from(r);
  }

  function _unsupportedIterableToArray(r, a) {
    if (r) {
      if ("string" == typeof r) return _arrayLikeToArray(r, a);
      var t = {}.toString.call(r).slice(8, -1);
      return "Object" === t && r.constructor && (t = r.constructor.name), "Map" === t || "Set" === t ? Array.from(r) : "Arguments" === t || /^(?:Ui|I)nt(?:8|16|32)(?:Clamped)?Array$/.test(t) ? _arrayLikeToArray(r, a) : void 0;
    }
  }

  function _nonIterableSpread() {
    throw new TypeError("Invalid attempt to spread non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.");
  }

  function _toConsumableArray(r) {
    return _arrayWithoutHoles(r) || _iterableToArray(r) || _unsupportedIterableToArray(r) || _nonIterableSpread();
  }

  var Button = (function (props) {
    var _props$configuration, _props$configuration2;
    var blockchains = typeof props.blockchains === 'string' ? JSON.parse(props.blockchains).map(function (name) {
      return Blockchains__default["default"][name];
    }) : (props.blockchains || []).map(function (name) {
      return Blockchains__default["default"][name];
    });
    if ((!blockchains || blockchains.length === 0) && (props !== null && props !== void 0 && (_props$configuration = props.configuration) !== null && _props$configuration !== void 0 && _props$configuration.accept || props !== null && props !== void 0 && (_props$configuration2 = props.configuration) !== null && _props$configuration2 !== void 0 && _props$configuration2.sell)) {
      blockchains = _toConsumableArray(new Set(props.configuration.accept.map(function (item) {
        return item.blockchain;
      }))).map(function (name) {
        return Blockchains__default["default"][name];
      });
    }
    return /*#__PURE__*/React__default["default"].createElement("div", null, /*#__PURE__*/React__default["default"].createElement("div", {
      className: "Row"
    }, /*#__PURE__*/React__default["default"].createElement("button", {
      onClick: props.onClick
    }, props.label)), /*#__PURE__*/React__default["default"].createElement("div", {
      className: "Row"
    }, blockchains.map(function (blockchain) {
      return /*#__PURE__*/React__default["default"].createElement("div", {
        key: blockchain.name,
        title: blockchain.label,
        className: "SupportedBlockchain",
        style: {
          backgroundColor: blockchain.logoBackgroundColor
        }
      }, /*#__PURE__*/React__default["default"].createElement("img", {
        src: blockchain.logo
      }));
    })));
  });

  var insideStyle = "\n  .ReactShadowDOMInsideContainer {\n    user-select: none;\n  }\n  \n  button {\n    background: #ea357a;\n    border-radius: 32px;\n    border: 1px solid transparent;\n    color: white;\n    cursor: pointer;\n    font-family: -apple-system, BlinkMacSystemFont, \"Segoe UI\", Roboto, \"Helvetica Neue\", Arial, sans-serif, \"Apple Color Emoji\", \"Segoe UI Emoji\", \"Segoe UI Symbol\";\n    font-size: 21px;\n    font-weight: 500;\n    min-width: 200px;\n    padding: 11px 32px;\n  }\n\n  button:hover {\n    box-shadow: inset 0 0 500px rgba(0,0,0,0.05);\n  }\n\n  button:active {\n    box-shadow: inset 0 0 500px rgba(0,0,0,0.1);\n  }\n\n  button.round {\n    border-radius: 32px;\n  }\n\n  button.rounded {\n    border-radius: 8px;\n  }\n\n  button.square {\n    border-radius: 0;\n  }\n\n  .Row {\n    line-height: 16px;\n  }\n\n  .SupportedBlockchain {\n    background: white;\n    border-radius: 4px;\n    display: inline-block;\n    height: 20px;\n    margin-right: 5px;\n    margin-top: 5px;\n    overflow: hidden;\n    width: 20px;\n    padding: 1px;\n  }\n\n  .SupportedBlockchain:last-child {\n    margin-right: 0;\n  }\n\n  strong {\n    font-weight: 800;\n    letter-spacing: -0.5px;\n  }\n";

  var outsideStyle = "\n  text-align: center;\n";

  function ownKeys(e, r) { var t = Object.keys(e); if (Object.getOwnPropertySymbols) { var o = Object.getOwnPropertySymbols(e); r && (o = o.filter(function (r) { return Object.getOwnPropertyDescriptor(e, r).enumerable; })), t.push.apply(t, o); } return t; }
  function _objectSpread(e) { for (var r = 1; r < arguments.length; r++) { var t = null != arguments[r] ? arguments[r] : {}; r % 2 ? ownKeys(Object(t), !0).forEach(function (r) { _defineProperty(e, r, t[r]); }) : Object.getOwnPropertyDescriptors ? Object.defineProperties(e, Object.getOwnPropertyDescriptors(t)) : ownKeys(Object(t)).forEach(function (r) { Object.defineProperty(e, r, Object.getOwnPropertyDescriptor(t, r)); }); } return e; }
  var DePayButton = (function (props) {
    var element = React.useRef(null);
    var label = props.label ? props.label : 'Pay';
    React.useEffect(function () {
      var unmount;
      if (element.current) {
        var _ReactShadowDOM = reactShadowDom.ReactShadowDOM({
          document: document,
          element: element.current,
          content: /*#__PURE__*/React__default["default"].createElement(Button, {
            label: label,
            onClick: onclickHandler,
            configuration: props.configuration || {},
            blockchains: props.blockchains
          }),
          outsideStyle: outsideStyle,
          insideStyle: insideStyle + " " + props.css
        });
        unmount = _ReactShadowDOM.unmount;
      }
      return function () {
        unmount ? unmount() : null;
      };
    }, [element, props]);
    var onclickHandler = function onclickHandler() {
      DePayWidgets__default["default"].Payment(_objectSpread(_objectSpread({}, props.configuration), {}, {
        integration: props.integration,
        payload: props.payload
      }));
    };
    return /*#__PURE__*/React__default["default"].createElement("div", {
      ref: element
    });
  });

  function init (_ref) {
    var document = _ref.document;
    Array.from(document.getElementsByClassName('DePayButton')).forEach(function (element) {
      if (element.getAttribute('initialized')) {
        return;
      }
      element.setAttribute('initialized', true);
      var label = element.getAttribute('label') || 'Pay';
      var blockchains = element.getAttribute('blockchains');
      var integration = element.getAttribute('integration');
      var payload = element.getAttribute('payload');
      var css = element.getAttribute('css');
      var configuration = JSON.parse(element.getAttribute('configuration') || '{}');
      if (integration) {
        configuration['integration'] = integration;
      }
      if (payload) {
        configuration['payload'] = payload;
      }
      var onclickHandler = function onclickHandler() {
        DePayWidgets__default["default"].Payment(configuration);
      };
      reactShadowDom.ReactShadowDOM({
        document: document,
        element: element,
        content: /*#__PURE__*/React__default["default"].createElement(Button, {
          label: label,
          onClick: onclickHandler,
          configuration: configuration,
          blockchains: blockchains
        }),
        outsideStyle: outsideStyle,
        insideStyle: insideStyle + " " + css
      });
    });
  }

  function createCommonjsModule(fn) {
    var module = { exports: {} };
  	return fn(module, module.exports), module.exports;
  }

  var client = createCommonjsModule(function (module, exports) {


  {
    exports.createRoot = m__default["default"].createRoot;
    exports.hydrateRoot = m__default["default"].hydrateRoot;
  }
  });

  var DePayButtons = {
    init: init,
    DePayButton: DePayButton,
    React: React__default["default"],
    createRoot: client.createRoot,
    DePayWidgets: DePayWidgets__default["default"]
  };

  return DePayButtons;

}));
