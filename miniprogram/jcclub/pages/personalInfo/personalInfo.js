//获取应用实例
const app = getApp()
Page({

  /**
   * 页面的初始数据
   */
  data: {
      talentInfo:{
      }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=query&userInfoId=' + app.globalData.userId,
      success: function (res) {
        console.log(res)
        that.setData({
          talentInfo: res.data.data
        })

      }
    })
  },
  saveInfo:function(){
    var that = this;
    console.log(that.data.talentInfo)
    wx.request({  
      url: app.globalData.requestUri + '/personal?actionName=save&userInfoId=' + app.globalData.userId,
      data: that.data.talentInfo,
      success: function (res) {
        if(res.data.resCode == '200'){
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
  publish: function () {
    wx.request({
      url: app.globalData.requestUri + '/personal?actionName=save',
      data: this.data.talentInfo,
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
  bindKeyInput: function (e) {
    console.log(e)
    this.setData({
      'talentInfo.name': e.detail.value
    })
  },
  keyInput: function (e) {
    var that = this;
    console.log(e)
    const propertyName = e.currentTarget.id;
    const talentInfo = Object.assign({}, this.data.talentInfo, { [propertyName]: e.detail.detail.value})
    console.log(propertyName)
    console.log(talentInfo)
    that.setData({
      talentInfo: talentInfo
    })
    console.log(talentInfo)
  }
  
})