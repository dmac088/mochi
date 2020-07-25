// LocalStorageService.js
const LocalStorageService = (function () {
  var _service;

  function _getService() {
    if (!_service) {
      _service = this;
      return _service
    }
    return _service
  }

  function _removeItem(item) {
    const bagItems = JSON.parse(localStorage.getItem('bagItems')) || [];
    localStorage.setItem('bagItems', bagItems.filter(i => i.productCode !== item.productCode));
  }

  function _clearItems() {
    localStorage.setItem('bagItems', []); 
  }

  function _getItems() {
    return JSON.parse(localStorage.getItem('bagItems')) || [];
  }

  function _setItems(items) {
    return localStorage.setItem('bagItems', items);
  }

  function _getItem(productCode) {
    const bagItems = JSON.parse(localStorage.getItem('bagItems'));
    return bagItems.filter(i => i.productCode === productCode);
  }

  return {
    setItems: _setItems,
    getItems: _getItems,
    getItem: _getItem,
    getService: _getService,
    removeItem: _removeItem,
    clearItems: _clearItems
  }
})();


export default LocalStorageService;