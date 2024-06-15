[![btyhub.site](https://img.shields.io/badge/demo-btyhub.site-blue)](https://www.btyhub.site)
# Introduction
A simple profile website with personal blog publishing and public file uploading

[Frontend](https://github.com/bty834/profile-nextjs): React(Next)+Axios+Tailwindcss. deployment pls see [Next.js项目部署，使用Nginx和pm2](https://blog.csdn.net/weixin_41866717/article/details/128357826)

[Backend](https://github.com/bty834/profile-spring-boot): Spring Boot + MySQL + Redis + S3 Object Storage

# Function
- Resume with timeline intro
- CRUD blog with markdown
- Record all types of files, backend using S3 Object Storage (impl: [filebase](https://filebase.com/)) or local Server Storage 
- Switch light/dark mode
- Inner-website search(todo)

Note: please add a `.env` file， and set the following constants (must start with `NEXT_PUBLIC_` in Next.js):
- `NEXT_PUBLIC_BASE_API` for Axios to fetch data
- `NEXT_PUBLIC_EMAIL` your email address
- `NEXT_PUBLIC_GITHUB` your GitHub address
- `NEXT_PUBLIC_GITEE` your Gitee address

Another Note: Filebase service is unavailable in mainland China, I'm looking for another available service...