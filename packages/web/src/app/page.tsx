import { getServerSession } from 'next-auth/next'
import { options } from './api/auth/[...nextauth]/options'
import GoToDashboard from '@/components/FloatButton/GoToDashboard'

import { Hero, Features } from '@/containers/main/mainLayout'
import Footer from '@/containers/main/Footer'
import CTA from '@/containers/main/CTA'
import TestimonialsSlider from '@/containers/main/TestimonialsSlider'
export default async function Page() {
  const session = await getServerSession(options)

  return (
    <>
      <main
      // className="flex w-full h-full "
      >
        <Hero />
        <Features />
        <TestimonialsSlider />
        <CTA />

        <GoToDashboard session={session} />
      </main>
      <Footer />
    </>
  )
}
