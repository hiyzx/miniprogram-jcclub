//获取应用实例
const app = getApp()

Page({

  /**
   * 页面的初始数据
   */
  data: {
     teamInfo:{
     }
  },

  publish: function () {
    console.log(app.globalData.userId)

    wx.request({
      url: app.globalData.requestUri + '/teamLibrary?actionName=publish&userInfoId=' + app.globalData.userId,
      data: this.data.teamInfo,
      success: function (res) {
        if (res.data.resCode == '200') {
          wx.showToast({
            title: '保存成功',
            icon: 'succes',
            duration: 1000,
            mask: true
          })
        }
      }
    })
  },


  keyInput: function (e) {
    var that = this;
    console.log(e)
    const propertyName = e.currentTarget.id;
    const teamInfo = Object.assign({}, this.data.teamInfo, { [propertyName]: e.detail.detail.value })
    console.log(propertyName)
    console.log(teamInfo)
    that.setData({
      teamInfo: teamInfo
    })
    console.log(teamInfo)
  }
})