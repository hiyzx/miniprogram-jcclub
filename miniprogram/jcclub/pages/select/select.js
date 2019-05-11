Component({
  properties: {
    itemList: {
      type: Array,
      value: "",
      observer: function (newVal, oldVal) { }
    },

  },
  data: {
    showDropItem: true,
    selectItemContent: "请选择"
  },
  methods: {
    showItemData() {
      this.setData({
        showDropItem: !this.data.showDropItem
      })
    },
    selectItem(e) {
      // console.log(e)
      let item = e.currentTarget.dataset.selectitem;
      this.setData({
        showDropItem: true,
        selectItemContent: item.name
      })
      this.triggerEvent("selectItem", item);//自定义方法,传值。传到使用页面时，通过e.detail拿取
    }
  }
})