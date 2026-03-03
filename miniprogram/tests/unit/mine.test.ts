import { mount } from '@vue/test-utils'
import Mine from '../../src/pages/mine.vue'

describe('mine.vue', () => {
  test('renders user profile title', () => {
    const wrapper = mount(Mine)
    expect(wrapper.find('h1').text()).toBe('个人中心')
  })

  test('renders user info section', () => {
    const wrapper = mount(Mine)
    expect(wrapper.find('.user-info').exists()).toBe(true)
  })

  test('renders settings section', () => {
    const wrapper = mount(Mine)
    expect(wrapper.find('.settings').exists()).toBe(true)
  })

  test('renders about section', () => {
    const wrapper = mount(Mine)
    expect(wrapper.find('.about').exists()).toBe(true)
  })
})
