//获取应用实例
const app = getApp()

// pages/team/team.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    teams: {}
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=myDelivery&userInfoId=' + app.globalData.userId,
      success: function (res) {
        that.setData({
          teams: res.data.data
        })
      }
    })
  }
})