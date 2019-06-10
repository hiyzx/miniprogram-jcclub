//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
        talents:{}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
      var that = this;
      wx.request({
          url: app.globalData.requestUri + '/talentLibrary?actionName=list',
          success: function (res) {
              that.setData({
                  talents:res.data.data
              })
          }
      })
  },
  onShow: function () {
    this.onLoad();
  }
})