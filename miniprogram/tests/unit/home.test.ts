import { mount } from '@vue/test-utils'
import Home from '../../src/pages/home.vue'

describe('home.vue', () => {
  test('renders home page title', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('h1').text()).toBe('家庭药箱')
  })

  test('renders quick access section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.quick-access').exists()).toBe(true)
  })

  test('renders recent medicines section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.recent-medicines').exists()).toBe(true)
  })

  test('renders alerts section', () => {
    const wrapper = mount(Home)
    expect(wrapper.find('.alerts').exists()).toBe(true)
  })
})
